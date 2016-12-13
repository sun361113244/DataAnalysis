package service.impl;

import entity.DescriptiveStatistic;
import entity.GroupRatio;
import entity.TbColumn;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ContinuousFeatureAnalysisService;
import service.DescriptiveStatisticsLocalService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DescriptiveStatisticsLocalJdbcImpl implements DescriptiveStatisticsLocalService
{
    @Autowired
    private List<ContinuousFeatureAnalysisService> continuousFeatureAnalysisServiceList;

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

        continuousFeatureAnalysisServiceList.get(0).selectContinuousStatisticResult(descriptiveStatistic ,rows , tbColumns , i);

        if( Math.abs(descriptiveStatistic.getMax() - descriptiveStatistic.getMin()) / (3.5 * descriptiveStatistic.getStandardDeviation() / Math.sqrt(descriptiveStatistic.getN())) > 5 )
        {
            continuousFeatureAnalysisServiceList.get(1).selectContinuousStatisticResult(descriptiveStatistic ,rows , tbColumns , i);
//            selectColumnExceptionValue(descriptiveStatistic ,rows , tbColumns , i);
        }
        if(descriptiveStatistic.getN() >= 10)
        {
            continuousFeatureAnalysisServiceList.get(2).selectContinuousStatisticResult(descriptiveStatistic ,rows , tbColumns , i);
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
}
