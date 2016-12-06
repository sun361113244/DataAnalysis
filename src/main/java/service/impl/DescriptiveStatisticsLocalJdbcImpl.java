package service.impl;

import entity.DescriptiveStatistic;
import entity.GroupRatio;
import entity.TbColumn;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.stereotype.Service;
import service.DescriptiveStatisticsLocalService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DescriptiveStatisticsLocalJdbcImpl implements DescriptiveStatisticsLocalService
{
    @Override
    public List<DescriptiveStatistic> selectDescriptiveStatistics(List<List<Object>> rows, List<TbColumn> tbColumns) throws SQLException
    {
        List<DescriptiveStatistic> descriptiveStatistics = new ArrayList<>();

        if(rows.size() == 0)
            return null;

        for(int i = 0 ;i < tbColumns.size(); i++ )
        {
            DescriptiveStatistic descriptiveStatistic = null;
            switch (tbColumns.get(i).getFeatureType())
            {
                case ID:
                    break;
                case CONTINUOUS:
                    descriptiveStatistic = selectContinuousDescriptiveStatisticResult(rows , tbColumns , i);
                    break;
                case CATEGORY:
                    descriptiveStatistic = selectCategoryDescriptiveStatisticResult(rows , tbColumns , i);
                    break;
                case DATE:
                    break;
                case DATETIME:
                    break;
                case TEXT:
                    break;
                case WORD:
                    break;
            }

            descriptiveStatistics.add(descriptiveStatistic);
        }
        return descriptiveStatistics;
    }

    private DescriptiveStatistic selectCategoryDescriptiveStatisticResult(List<List<Object>> rows, List<TbColumn> tbColumns, int i)
    {
        int groupCount = 0;
        Map<String , Integer> groupMap = new HashedMap();

        for(List<Object> row : rows)
        {
            if(row.get(i) != null)
            {
                if(groupMap.containsKey(row.get(i)))
                    groupMap.put(row.get(i).toString() , groupMap.get(row.get(i).toString()) + 1);
                else
                    groupMap.put(row.get(i).toString() , 1);
                groupCount++;
            }
            else
            {
                if(groupMap.containsKey("NULL"))
                    groupMap.put("NULL" , groupMap.get("NULL") + 1);
                else
                    groupMap.put("NULL" , 1);
                groupCount++;
            }
        }

        List<GroupRatio> groupRatios = new ArrayList<>();
        for(Map.Entry<String , Integer> entry : groupMap.entrySet())
        {
            GroupRatio groupRatio = new GroupRatio();
            groupRatio.setCol_name(entry.getKey());
            groupRatio.setCount(entry.getValue());
            groupRatio.setRatio(((double)entry.getValue()/groupCount));

            groupRatios.add(groupRatio);
        }
        DescriptiveStatistic descriptiveStatistic = new DescriptiveStatistic();
        descriptiveStatistic.setTbCol(tbColumns.get(i).clone());
        descriptiveStatistic.setGroupRatios(groupRatios);
        descriptiveStatistic.setN(groupCount);

        return descriptiveStatistic;
    }

    private DescriptiveStatistic selectContinuousDescriptiveStatisticResult(List<List<Object>> rows, List<TbColumn> tbColumns, int i)
    {
        DescriptiveStatistic descriptiveStatistic = new DescriptiveStatistic();

        selectColumnSummary(descriptiveStatistic ,rows , tbColumns , i);

        if(descriptiveStatistic.getN() >= 30)
        {
            selectColumnFrequentDiagram(descriptiveStatistic ,rows , tbColumns , i);
            selectColumnExceptionValue(descriptiveStatistic ,rows , tbColumns , i);
        }

        return descriptiveStatistic;
    }

    private void selectColumnExceptionValue(DescriptiveStatistic descriptiveStatistic, List<List<Object>> rows, List<TbColumn> tbColumns, int i)
    {
        List<Double> exceptionVals = new ArrayList<>();
        double whisker_lower = descriptiveStatistic.getQuarter1() - 1.5 * (descriptiveStatistic.getQuarter3() - descriptiveStatistic.getQuarter1());
        double whisker_upper = descriptiveStatistic.getQuarter3() + 1.5 * (descriptiveStatistic.getQuarter3() - descriptiveStatistic.getQuarter1());
        for(List<Object> row : rows)
        {
            if(row.get(i) != null)
            {
                double val = Double.parseDouble(row.get(i).toString());
                if(val > whisker_upper || val <  whisker_lower)
                {
                    if(!exceptionVals.contains(val))
                        exceptionVals.add(val);
                }
            }
        }
        descriptiveStatistic.setExceptionVals(exceptionVals);
    }

    private void selectColumnFrequentDiagram(DescriptiveStatistic descriptiveStatistic, List<List<Object>> rows, List<TbColumn> tbColumns, int i)
    {
        int groupNum = (int) Math.sqrt(descriptiveStatistic.getN());
        groupNum = groupNum < 12 ? 12 : groupNum;
        groupNum = groupNum > 50 ? 50 : groupNum;

        double interval = (descriptiveStatistic.getMax() - descriptiveStatistic.getMin()) / groupNum;
        double[] interval_upper = new double[groupNum];
        interval_upper[0] = descriptiveStatistic.getMin();
        for(int j = 1; j < groupNum; j++)
        {
            interval_upper[j] = interval_upper[j-1] + interval;
        }
        Map<Double , Integer> pMap = new HashedMap();
        double whisker_lower = descriptiveStatistic.getQuarter1() - 1.5 * (descriptiveStatistic.getQuarter3() - descriptiveStatistic.getQuarter1());
        double whisker_upper = descriptiveStatistic.getQuarter3() + 1.5 * (descriptiveStatistic.getQuarter3() - descriptiveStatistic.getQuarter1());
        for(List<Object> row : rows)
        {
            if(row.get(i) != null)
            {
                double val = Double.parseDouble(row.get(i).toString());
                for(int k = 0; k < groupNum - 1; k++ )
                {
                    if(val >= interval_upper[k] && val < interval_upper[k+1])
                    {
                        if(pMap.containsKey(interval_upper[k]))
                            pMap.put(interval_upper[k] , pMap.get(interval_upper[k]) + 1);
                        else
                            pMap.put(interval_upper[k] , 1);
                    }
                }
            }
        }
        List<GroupRatio> frequencyRation = new ArrayList<>();
        for(Map.Entry<Double , Integer> entry : pMap.entrySet())
        {
            GroupRatio groupRatio = new GroupRatio();
            groupRatio.setCol_name(String.format("%.2f",
                    entry.getKey() == 0 ? 0 : entry.getKey()));
            groupRatio.setCount(entry.getValue());
            groupRatio.setRatio((double) entry.getValue() / descriptiveStatistic.getN());

            frequencyRation.add(groupRatio);
        }

        descriptiveStatistic.setGroupRatios(frequencyRation);
    }

    private void selectColumnSummary(DescriptiveStatistic descriptiveStatistic, List<List<Object>> rows, List<TbColumn> tbColumns, int i)
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
