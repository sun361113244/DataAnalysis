package service;

import entity.AnalysisFilter;
import entity.DescriptiveStatistic;
import entity.TbColumn;

import java.util.List;

public interface ContinuousFeatureAnalysisService
{
    void selectContinuousStatisticResult(DescriptiveStatistic descriptiveStatistic, List<List<Object>> rows, List<TbColumn> tbColumns, AnalysisFilter analysisFilter, int i);
}
