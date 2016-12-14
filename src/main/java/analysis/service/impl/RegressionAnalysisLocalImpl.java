package analysis.service.impl;

import analysis.entity.CorrelationAnalysis;
import analysis.entity.FeatureType;
import analysis.entity.TbColumn;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.stereotype.Service;
import analysis.service.RegressionAnalysis;

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
                    correlationAnalysis.setN(regression.getN());
                    correlationAnalysis.setSlope(regression.getSlope());
                    correlationAnalysis.setSlopeStdErr(regression.getSlopeStdErr());
                    correlationAnalysis.setSlopeConfidenceIntercal(regression.getSlopeConfidenceInterval());
                    correlationAnalysis.setIntercept(regression.getIntercept());
                    correlationAnalysis.setInterceptStdErr(regression.getInterceptStdErr());
                    correlationAnalysis.setR(regression.getR());
                    correlationAnalysis.setrSquare(regression.getRSquare());
                    correlationAnalysis.setSignificance(regression.getSignificance());
                    correlationAnalysis.setMeanSquareError(regression.getMeanSquareError());
                    correlationAnalysis.setRegressionSumSquare(regression.getRegressionSumSquares());
                    correlationAnalysis.setSumofCrossProducts(regression.getSumOfCrossProducts());
                    correlationAnalysis.setSumSquaredErrors(regression.getSumSquaredErrors());
                    correlationAnalysis.setTotalSumSquares(regression.getTotalSumSquares());
                    correlationAnalysis.setxSumSquares(regression.getXSumSquares());

                    correlationAnalysises.add(correlationAnalysis);
                }
            }
        }
        return correlationAnalysises;
    }
}
