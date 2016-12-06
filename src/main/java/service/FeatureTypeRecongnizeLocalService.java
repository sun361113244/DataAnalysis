package service;

import entity.FeatureType;
import entity.TbColumn;

import java.sql.SQLException;
import java.util.List;

public interface FeatureTypeRecongnizeLocalService
{
    FeatureType recongnizeFeatureType(List<List<Object>> dataLists, TbColumn tbColumn);
}
