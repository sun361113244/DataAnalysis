package analysis.service.impl;

import analysis.entity.*;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.stereotype.Service;
import analysis.service.CorrelationAnalysisLocalService;
import util.Config;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CorrelationAnalysisLocalJdbcServiceImpl implements CorrelationAnalysisLocalService
{
    @Override
    public List<CorrelationAnalysis> selectSummaryInfo(List<List<Object>> rows, List<TbColumn> tbColumns, List<DescriptiveStatistic> descriptiveStatistics, AnalysisFilter analysisFilter) throws SQLException
    {
        List<CorrelationAnalysis> correlationAnalysises = new ArrayList<>();

        for(int i = 0 ;i < tbColumns.size(); i++ )
        {
            for(int j = i + 1 ;j < tbColumns.size(); j++ )
            {
                if(tbColumns.get(i).getFeatureType() == FeatureType.CONTINUOUS &&
                        tbColumns.get(j).getFeatureType() == FeatureType.CONTINUOUS)
                {
                    SimpleRegression regression = new SimpleRegression();
                    List<XYPoint> xyPoints = new ArrayList<>();

                    int interval = rows.size() > Config.REGRESSION_SIMPLING_MAX_COUNT ? rows.size() / Config.REGRESSION_SIMPLING_MAX_COUNT : 1;
                    int num = 0;
                    for(List<Object> row : rows)
                    {
                        if(row.get(i) != null && row.get(j) != null)
                        {
                            regression.addData(Double.parseDouble(row.get(i).toString()) , Double.parseDouble(row.get(j).toString()));

                            if((num++) % interval == 0)
                            {
                                XYPoint xyPoint = new XYPoint(Double.parseDouble(row.get(i).toString()) , Double.parseDouble(row.get(j).toString()));
                                xyPoints.add(xyPoint);
                            }
                        }
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
                    correlationAnalysis.setXyPoint(xyPoints);

                    correlationAnalysises.add(correlationAnalysis);

                }
            }
        }
        return correlationAnalysises;
    }
}
