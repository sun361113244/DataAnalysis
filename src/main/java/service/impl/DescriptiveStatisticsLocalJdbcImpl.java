package service.impl;

import entity.DescriptiveStatistic;
import entity.FeatureType;
import entity.GroupRatio;
import entity.TbColumn;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.spark.sql.execution.columnar.STRING;
import org.springframework.stereotype.Service;
import service.DescriptiveStatisticsLocalService;
import util.JdbcUtil;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DescriptiveStatisticsLocalJdbcImpl implements DescriptiveStatisticsLocalService
{
    @Resource
    private JdbcUtil jdbcUtil;

    @Override
    public List<DescriptiveStatistic> selectDescriptiveStatistics(String tblName, List<TbColumn> tbColumns) throws SQLException
    {
        List<DescriptiveStatistic> descriptiveStatistics = new ArrayList<>();
        List<List<Object>> rows = jdbcUtil.findMoreResult(String.format("select * from %s" , tblName) ,null);

        for(int i = 0 ;i < tbColumns.size(); i++ )
        {
            if(tbColumns.get(i).getFeatureType() == FeatureType.CONTINUOUS)
            {
                DescriptiveStatistics stats = new DescriptiveStatistics();
                for(List<Object> row : rows)
                {
                    stats.addValue(Double.parseDouble(row.get(i).toString()));
                }

                DescriptiveStatistic descriptiveStatistic = new DescriptiveStatistic();
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
//                descriptiveStatistic.setPercentiles(stats.getPercentile(10));

                descriptiveStatistic.setSkewness(stats.getSkewness());
                descriptiveStatistic.setKurtosis(stats.getKurtosis());
//                descriptiveStatistic.setMedian(stats.m);

                double interval = (stats.getMax() - stats.getMin()) / 12;
                double[] interval_upper = new double[12];
                interval_upper[0] = stats.getMin();
                for(int j = 1; j < 12; j++)
                {
                    interval_upper[j] = interval_upper[j-1] + interval;
                }

                Map<Double , Integer> pMap = new HashedMap();
                for(List<Object> row : rows)
                {
                    double val = Double.parseDouble(row.get(i).toString());
                    for(int k = 0; k < 11; k++ )
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
                List<GroupRatio> frequencyRation = new ArrayList<>();
                for(Map.Entry<Double , Integer> entry : pMap.entrySet())
                {
                    GroupRatio groupRatio = new GroupRatio();
                    groupRatio.setCol_name(String.format("%.2f",
                            entry.getKey() == 0 ? 0 : entry.getKey()));
                    groupRatio.setCount(entry.getValue());
                    groupRatio.setRatio((double) entry.getValue() / stats.getN());

                    frequencyRation.add(groupRatio);
                }
                descriptiveStatistic.setGroupRatios(frequencyRation);

                descriptiveStatistics.add(descriptiveStatistic);
            }

            if(tbColumns.get(i).getFeatureType() == FeatureType.CATEGORY)
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

                descriptiveStatistics.add(descriptiveStatistic);
            }
        }
        return descriptiveStatistics;
    }
}
