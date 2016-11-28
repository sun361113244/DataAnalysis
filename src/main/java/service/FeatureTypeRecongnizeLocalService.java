package service;

import entity.FeatureType;

import java.sql.SQLException;

public interface FeatureTypeRecongnizeLocalService
{
    FeatureType recongnizeFeatureType(String tableName, int colNum, String col_name) throws SQLException;
}
