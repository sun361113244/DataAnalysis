package service;

import entity.DescriptiveStatistic;
import entity.TbColumn;

import java.sql.SQLException;
import java.util.List;

public interface DescriptiveStatisticsLocalService
{
    List<DescriptiveStatistic> selectDescriptiveStatistics(List<List<Object>> rows, List<TbColumn> tbColumns) throws SQLException;
}
