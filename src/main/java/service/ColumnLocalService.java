package service;

import entity.TbColumn;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.sql.SQLException;
import java.util.List;

public interface ColumnLocalService
{
    List<TbColumn> loadSchema(String tableName) throws SQLException;
}
