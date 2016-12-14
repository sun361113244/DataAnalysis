package analysis.service;

import analysis.entity.AnalysisFilter;
import analysis.entity.DescriptiveStatistic;
import analysis.entity.TbColumn;

import java.util.List;

public interface ContinuousFeatureAnalysisService
{
    void selectContinuousStatisticResult(DescriptiveStatistic descriptiveStatistic, List<List<Object>> rows, List<TbColumn> tbColumns, AnalysisFilter analysisFilter, int i);
}
