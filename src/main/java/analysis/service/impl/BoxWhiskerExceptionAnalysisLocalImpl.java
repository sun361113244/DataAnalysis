package analysis.service.impl;

import analysis.entity.AnalysisFilter;
import analysis.entity.DescriptiveStatistic;
import analysis.entity.ExceptionValue;
import analysis.entity.TbColumn;
import analysis.service.ContinuousFeatureAnalysisService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Order(value = 5)
@Service
public class BoxWhiskerExceptionAnalysisLocalImpl implements ContinuousFeatureAnalysisService
{

    @Override
    public void selectContinuousStatisticResult(DescriptiveStatistic descriptiveStatistic, List<List<Object>> rows, List<TbColumn> tbColumns, AnalysisFilter analysisFilter, int i)
    {
        List<ExceptionValue> exceptionValues = new ArrayList<>();
        double whisker_lower = descriptiveStatistic.getQuarter1() - 1.5 * (descriptiveStatistic.getQuarter3() - descriptiveStatistic.getQuarter1());
        double whisker_upper = descriptiveStatistic.getQuarter3() + 1.5 * (descriptiveStatistic.getQuarter3() - descriptiveStatistic.getQuarter1());
        for(List<Object> row : rows)
        {
            if(row.get(i) != null)
            {
                double val = Double.parseDouble(row.get(i).toString());
                if(val > whisker_upper)
                {
                    if(!exceptionValues.contains(val))
                    {
                        ExceptionValue value = new ExceptionValue();
                        value.setValue(val);
                        value.setExceptionStat(1);

                        exceptionValues.add(value);
                    }
                }
                if(val <  whisker_lower)
                {
                    if(!exceptionValues.contains(val))
                    {
                        ExceptionValue value = new ExceptionValue();
                        value.setValue(val);
                        value.setExceptionStat(-1);

                        exceptionValues.add(value);
                    }
                }
            }
        }
        descriptiveStatistic.setExceptionValues(exceptionValues);
    }
}
