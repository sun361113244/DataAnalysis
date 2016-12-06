package service;

import entity.TbColumn;

import java.sql.SQLException;
import java.util.List;

public interface ColumnLocalService
{
    List<TbColumn> loadSchema(String tableName, List<List<Object>> dataLists) throws SQLException;
}
