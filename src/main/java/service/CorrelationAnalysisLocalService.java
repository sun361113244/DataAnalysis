package service;

import entity.CorrelationAnalysis;
import entity.TbColumn;

import java.sql.SQLException;
import java.util.List;

public interface CorrelationAnalysisLocalService
{
    List<CorrelationAnalysis> selectSummaryInfo(List<List<Object>> tblName, List<TbColumn> tbColumns) throws SQLException;
}
