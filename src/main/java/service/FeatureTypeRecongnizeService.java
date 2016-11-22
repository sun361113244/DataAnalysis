package service;

import entity.FeatureType;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public interface FeatureTypeRecongnizeService
{
    FeatureType recongnizeFeatureType(Dataset<Row> jdbcDF, String name, int col_index);
}
