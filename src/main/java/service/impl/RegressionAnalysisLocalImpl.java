package service.impl;

import entity.CorrelationAnalysis;
import entity.FeatureType;
import entity.TbColumn;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.stereotype.Service;
import service.RegressionAnalysis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service("RegressionAnalysisLocalImpl")
public class RegressionAnalysisLocalImpl implements RegressionAnalysis,Serializable
{
    @Override
    public List<CorrelationAnalysis> analysis(Dataset<Row> jdbcDF, List<TbColumn> tbColumns, double r2_upper)
    {
        List<CorrelationAnalysis> correlationAnalysises = new ArrayList<>();
        List<Row> jdbcRows = jdbcDF.collectAsList();

        for(int i = 0 ;i < tbColumns.size(); i++ )
        {
            for(int j = i + 1 ;j < tbColumns.size(); j++ )
            {
                if(tbColumns.get(i).getFeatureType() == FeatureType.CONTINUOUS &&
                        tbColumns.get(j).getFeatureType() == FeatureType.CONTINUOUS)
                {
                    SimpleRegression regression = new SimpleRegression();

                    for(Row row : jdbcRows)
                    {
                        regression.addData(Double.parseDouble(row.get(i).toString()) , Double.parseDouble(row.get(j).toString()));
                    }

                    CorrelationAnalysis correlationAnalysis = new CorrelationAnalysis();
                    correlationAnalysis.setTb_col1(tbColumns.get(i).clone());
                    correlationAnalysis.setTb_col2(tbColumns.get(j).clone());
                    correlationAnalysis.setSST(regression.getMeanSquareError());
                    correlationAnalysis.setSSE(regression.getSlopeStdErr());
                    correlationAnalysis.setSSR(regression.getTotalSumSquares());
                    correlationAnalysis.setR2(regression.getRSquare());
                    correlationAnalysis.setParam_gradient(regression.getSlope());
                    correlationAnalysis.setParam_intercept(regression.getIntercept());

                    if(correlationAnalysis.getR2() >= r2_upper)
                        correlationAnalysises.add(correlationAnalysis);
                }
            }
        }
        return correlationAnalysises;
    }
}
