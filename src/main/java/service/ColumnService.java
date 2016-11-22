package service;

import entity.TbColumn;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.util.List;

public interface ColumnService
{
    List<TbColumn> loadSchema(Dataset<Row> jdbcDF);
}
