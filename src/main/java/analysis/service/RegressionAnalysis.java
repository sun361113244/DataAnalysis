package analysis.service;

import analysis.entity.CorrelationAnalysis;
import analysis.entity.TbColumn;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.util.List;

public interface RegressionAnalysis
{
    List<CorrelationAnalysis> analysis(Dataset<Row> jdbcDF, List<TbColumn> tbColumns, double r2_upper);
}
