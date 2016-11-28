package service.impl;

import entity.CorrelationAnalysis;
import entity.FeatureType;
import entity.TbColumn;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.spark.sql.Row;
import org.springframework.stereotype.Service;
import service.CorrelationAnalysisLocalService;
import util.JdbcUtil;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CorrelationAnalysisLocalJdbcServiceImpl implements CorrelationAnalysisLocalService
{
    @Resource
    private JdbcUtil jdbcUtil;

    @Override
    public List<CorrelationAnalysis> selectSummaryInfo(String tblName, List<TbColumn> tbColumns, double r2_upper) throws SQLException
    {
        List<CorrelationAnalysis> correlationAnalysises = new ArrayList<>();
        List<List<Object>> rows = jdbcUtil.findMoreResult(String.format("select * from %s" , tblName) ,null);

        for(int i = 0 ;i < tbColumns.size(); i++ )
        {
            for(int j = i + 1 ;j < tbColumns.size(); j++ )
            {
                if(tbColumns.get(i).getFeatureType() == FeatureType.CONTINUOUS &&
                        tbColumns.get(j).getFeatureType() == FeatureType.CONTINUOUS)
                {
                    SimpleRegression regression = new SimpleRegression();

                    for(List<Object> row : rows)
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
