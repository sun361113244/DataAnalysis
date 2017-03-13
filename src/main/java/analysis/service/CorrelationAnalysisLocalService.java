package analysis.service;

import analysis.entity.AnalysisFilter;
import analysis.entity.CorrelationAnalysis;
import analysis.entity.DescriptiveStatistic;
import analysis.entity.TbColumn;

import java.sql.SQLException;
import java.util.List;

public interface CorrelationAnalysisLocalService
{
    List<CorrelationAnalysis> selectSummaryInfo(List<List<Object>> tblName, List<TbColumn> tbColumns, List<DescriptiveStatistic> descriptiveStatistics, AnalysisFilter analysisFilter) throws SQLException;
}
