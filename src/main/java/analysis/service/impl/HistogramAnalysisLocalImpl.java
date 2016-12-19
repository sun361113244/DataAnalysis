package analysis.service.impl;

import analysis.entity.*;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import analysis.service.ContinuousFeatureAnalysisService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Order(value = 1)
@Service
public class HistogramAnalysisLocalImpl implements ContinuousFeatureAnalysisService
{
    @Override
    public void selectContinuousStatisticResult(DescriptiveStatistic descriptiveStatistic,
                                                List<List<Object>> rows, List<TbColumn> tbColumns, AnalysisFilter analysisFilter, int i)
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

        FrequencyHistogramAnalysis frequencyHistogramAnalysis = new FrequencyHistogramAnalysis();
        List<FrequencyUnit> frequencyUnits = new ArrayList<>();
        for(Map.Entry<Double , Integer> entry : pMap.entrySet())
        {
            FrequencyUnit frequencyUnit = new FrequencyUnit();
            frequencyUnit.setUnitStart(entry.getKey());
            frequencyUnit.setInterval(interval);
            frequencyUnit.setCount(entry.getValue());
            frequencyUnit.setRatio((double) entry.getValue() / descriptiveStatistic.getN());

            frequencyUnits.add(frequencyUnit);
        }

        Collections.sort(frequencyUnits);
        frequencyHistogramAnalysis.setTbColumn(tbColumns.get(i).clone());
        frequencyHistogramAnalysis.setFrequencyUnits(frequencyUnits);
        descriptiveStatistic.setFrequencyHistogramAnalysis(frequencyHistogramAnalysis);
    }
}
