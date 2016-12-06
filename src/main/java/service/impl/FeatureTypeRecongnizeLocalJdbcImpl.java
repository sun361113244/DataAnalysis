package service.impl;

import entity.FeatureType;
import entity.TbColumn;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;
import service.FeatureTypeRecongnizeLocalService;
import util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FeatureTypeRecongnizeLocalJdbcImpl implements FeatureTypeRecongnizeLocalService
{
    @Override
    public FeatureType recongnizeFeatureType(List<List<Object>> dataLists, TbColumn tbColumn)
    {
        if(isIDType(dataLists , tbColumn))
            return FeatureType.ID;

        if(isCategoryType(dataLists , tbColumn))
            return FeatureType.CATEGORY;

        if(isDateType(dataLists , tbColumn))
            return FeatureType.DATE;

        if(isContinuousType(dataLists , tbColumn))
            return FeatureType.CONTINUOUS;

        return FeatureType.TEXT;
    }

    private boolean isContinuousType(List<List<Object>> dataLists, TbColumn tbColumn)
    {
        if(tbColumn.getCol_type().equals("java.lang.Integer") ||
                tbColumn.getCol_type().equals("java.lang.Long") ||
                tbColumn.getCol_type().equals("java.lang.Double") ||
                tbColumn.getCol_type().equals("java.lang.Float"))
        {
            for(List<Object> list : dataLists)
            {
                if(list.get(tbColumn.getCol_num()) != null && !StringUtil.isNumeric(list.get(tbColumn.getCol_num()).toString()))
                    return false;
            }
            return true;
        }
        return false;
    }

    private boolean isDateType(List<List<Object>> dataLists, TbColumn tbColumn)
    {
        return false;
    }

    private boolean isCategoryType(List<List<Object>> dataLists, TbColumn tbColumn)
    {
        if(tbColumn.getCol_type().equals("java.lang.Integer") ||
                tbColumn.getCol_type().equals("java.lang.Long") ||
                tbColumn.getCol_type().equals("java.lang.String"))
        {
            List<Object> groupList = groupBylist(dataLists , tbColumn.getCol_num());
            double threshhold = Math.sqrt((double)dataLists.size()) > 10 ? Math.sqrt((double) dataLists.size()) : 10;
            if(groupList != null && groupList.size() <= threshhold)
                return true;
            else
                return false;
        }
        return false;
    }

    private boolean isIDType(List<List<Object>> dataLists, TbColumn tbColumn)
    {
        if(tbColumn.getCol_num() == 0 &&
                tbColumn.getCol_name().toLowerCase().contains("id") &&
                (tbColumn.getCol_type().equals("java.lang.Integer") || tbColumn.getCol_type().equals("java.lang.Long")) &&
                !tbColumn.isNullable())
        {
            return true;
        }
        return false;
    }

    private List<Object> groupBylist(List<List<Object>> dataLists, int colNum)
    {
        if(CollectionUtils.isEmpty(dataLists))
            return null;

        List<Object> result = new ArrayList<>();
        Map<Object , Integer> map = new HashedMap();

        for(List<Object> obj : dataLists)
        {
            if(map.containsKey(obj.get(colNum)))
                map.put(obj.get(colNum) , map.get(obj.get(colNum)) + 1);
            else
                map.put(obj.get(colNum) , 1);
        }
        for(Map.Entry entry : map.entrySet())
        {
            result.add(entry.getKey());
        }

        return result;
    }
}
