package service;

import entity.FeatureType;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructField;

public interface FeatureTypeRecongnizeService
{
    FeatureType recongnizeFeatureType(Dataset<Row> jdbcDF, StructField structField, int col_index);
}
