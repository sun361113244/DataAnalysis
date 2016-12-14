package analysis.service.impl;

import analysis.entity.AnalysisFilter;
import analysis.entity.DescriptiveStatistic;
import analysis.entity.TbColumn;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import analysis.service.ContinuousFeatureAnalysisService;

import java.util.List;

@Order(value = 1)
@Service
public class DescriptiveAnalysisLocalImpl implements ContinuousFeatureAnalysisService
{
    @Override
    public void selectContinuousStatisticResult(DescriptiveStatistic descriptiveStatistic, List<List<Object>> rows,
                                                List<TbColumn> tbColumns, AnalysisFilter analysisFilter, int i)
    {
        DescriptiveStatistics stats = new DescriptiveStatistics();

        for(List<Object> row : rows)
        {
            if(row.get(i) != null)
                stats.addValue(Double.parseDouble(row.get(i).toString()));
        }

        descriptiveStatistic.setTbCol(tbColumns.get(i).clone());
        descriptiveStatistic.setMin(stats.getMin());
        descriptiveStatistic.setMax(stats.getMax());
        descriptiveStatistic.setMean(stats.getMean());
        descriptiveStatistic.setGeometricMean(stats.getGeometricMean());
        descriptiveStatistic.setN(stats.getN());
        descriptiveStatistic.setSum(stats.getSum());
        descriptiveStatistic.setSumOfSquares(stats.getSumsq());
        descriptiveStatistic.setStandardDeviation(stats.getStandardDeviation());
        descriptiveStatistic.setVariance(stats.getVariance());
        descriptiveStatistic.setSkewness(stats.getSkewness());
        descriptiveStatistic.setKurtosis(stats.getKurtosis());
        descriptiveStatistic.setMedian(stats.getPercentile(50));
        descriptiveStatistic.setQuarter1(stats.getPercentile(25));
        descriptiveStatistic.setQuarter3(stats.getPercentile(75));
    }
}
