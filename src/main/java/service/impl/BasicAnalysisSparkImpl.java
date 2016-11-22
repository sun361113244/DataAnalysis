package service.impl;

import entity.BasicAnalysis;
import entity.TbColumn;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.stat.MultivariateStatisticalSummary;
import org.apache.spark.mllib.stat.Statistics;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;
import service.BasicAnalysisService;
import util.Config;
import util.SparkContextBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class BasicAnalysisSparkImpl implements BasicAnalysisService ,Serializable
{
    public List<BasicAnalysis> selectSummaryInfo(String dBName, String tblName, List<TbColumn> cols)
    {
        Dataset<Row> jdbcDF = SparkContextBean.getSparkSession().read()
                .format("jdbc")
                .option("driver" , Config.JDBCDRIVERCLASSNAME)
                .option("url", Config.JDBCURL)
                .option("fetchSize" , 1000)
                .option("dbtable", "iris")
                .option("user", Config.JDBCUSERNAME)
                .option("password", Config.JDBCPASSWORD)
                .load();

        JavaRDD<Row> jdbcRow = jdbcDF.javaRDD();

        JavaRDD<Vector> jdbcVector = jdbcRow.map(
                new Function<Row, Vector>()
                {
                    public Vector call(Row row) throws Exception
                    {
                        Vector dv = Vectors.dense(Double.valueOf((double) row.getInt(0))  , row.getDouble(1) , row.getDouble(2) , row.getDouble(3)
                                , row.getDouble(4));
                        return dv;
                    }
                }
        );
        MultivariateStatisticalSummary summary = Statistics.colStats(jdbcVector.rdd());


        List<BasicAnalysis> basicAnalysises = new ArrayList<BasicAnalysis>();
        for(int i = 0;i < cols.size() - 1;i++)
        {
            BasicAnalysis basicAnalysis = new BasicAnalysis();
            basicAnalysis.setTb_col(cols.get(i));
            basicAnalysis.setMean(summary.mean().apply(i));
            basicAnalysis.setVariance(summary.variance().apply(i));
            basicAnalysis.setNumNonzeros((int)summary.numNonzeros().apply(i));
            basicAnalysis.setMax(summary.max().apply(i));
            basicAnalysis.setMin(summary.min().apply(i));
            basicAnalysis.setNormL1(summary.normL1().apply(i));
            basicAnalysis.setNormL2(summary.normL2().apply(i));

            basicAnalysises.add(basicAnalysis);
        }

        return basicAnalysises;
    }
}
