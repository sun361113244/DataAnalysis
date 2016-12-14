package analysis.service;

import analysis.entity.FeatureType;
import analysis.entity.TbColumn;

import java.util.List;

public interface FeatureTypeRecongnizeLocalService
{
    FeatureType recongnizeFeatureType(List<List<Object>> dataLists, TbColumn tbColumn);
}
