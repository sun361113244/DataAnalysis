package service.impl;

import entity.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Order(value = 2)
@Service
public class HistogramCDFAnalysisLocalImpl extends HistogramAnalysisLocalImpl
{
    @Override
    public void selectContinuousStatisticResult(DescriptiveStatistic descriptiveStatistic, List<List<Object>> rows, List<TbColumn> tbColumns, AnalysisFilter analysisFilter, int i)
    {
        super.selectContinuousStatisticResult(descriptiveStatistic , rows , tbColumns , analysisFilter, i);

        if(descriptiveStatistic.getFrequencyHistogramAnalysis() != null &&
                descriptiveStatistic.getFrequencyHistogramAnalysis().getFrequencyUnits() != null)
        {
            int count = 0;
            double ratio = 0;
            List<FrequencyUnit> cdfPoints = new ArrayList<>();
            for(FrequencyUnit frequencyUnit :descriptiveStatistic.getFrequencyHistogramAnalysis().getFrequencyUnits())
            {
                count += frequencyUnit.getCount();
                ratio = ratio + frequencyUnit.getRatio();

                FrequencyUnit current = new FrequencyUnit();
                current.setUnitStart(frequencyUnit.getUnitStart());
                current.setInterval(frequencyUnit.getInterval());
                current.setCount(count);
                current.setRatio(ratio);

                cdfPoints.add(current);
            }
            descriptiveStatistic.getFrequencyHistogramAnalysis().setCdfs(cdfPoints);
        }
    }
}
