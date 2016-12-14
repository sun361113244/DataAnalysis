package analysis.service;

import analysis.entity.AnalysisFilter;
import analysis.entity.DescriptiveStatistic;
import analysis.entity.TbColumn;

import java.sql.SQLException;
import java.util.List;

public interface DescriptiveStatisticsLocalService
{
    List<DescriptiveStatistic> selectDescriptiveStatistics(List<List<Object>> rows, List<TbColumn> tbColumns,
                                                           AnalysisFilter analysisFilter) throws SQLException;
}
