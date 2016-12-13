package service.impl;

import entity.DescriptiveStatistic;
import entity.GroupRatio;
import entity.TbColumn;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import service.ContinuousFeatureAnalysisService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Order(value = 2)
@Service
public class FrequencyDiagramAnalysisLocalImpl implements ContinuousFeatureAnalysisService
{

    @Override
    public void selectContinuousStatisticResult(DescriptiveStatistic descriptiveStatistic ,
                                                List<List<Object>> rows, List<TbColumn> tbColumns, int i)
    {
        double interval = 3.5 * descriptiveStatistic.getStandardDeviation() / Math.sqrt(descriptiveStatistic.getN());
        int groupNum = (int) (Math.abs(descriptiveStatistic.getMax() - descriptiveStatistic.getMin()) / interval > 100 ? 100 :
                Math.abs(descriptiveStatistic.getMax() - descriptiveStatistic.getMin()) / interval) + 1;

        double[] interval_upper = new double[groupNum + 1];

        interval_upper[0] = descriptiveStatistic.getMin();
        interval_upper[groupNum - 1] = descriptiveStatistic.getMax();
        interval_upper[groupNum] = descriptiveStatistic.getMax() + interval;

        for(int j = 1; j < groupNum - 1; j++)
        {
            interval_upper[j] = interval_upper[j-1] + interval;
        }
        Map<Double , Integer> pMap = new HashedMap();

        for(List<Object> row : rows)
        {
            if(row.get(i) != null)
            {
                double val = Double.parseDouble(row.get(i).toString());
                for(int k = 0; k < groupNum ; k++ )
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
}
