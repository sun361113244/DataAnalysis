package service.impl;

import entity.FeatureType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;
import service.FeatureTypeRecongnizeLocalService;
import util.JdbcUtil;
import util.StringUtil;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.*;

@Service
public class FeatureTypeRecongnizeLocalJdbcImpl implements FeatureTypeRecongnizeLocalService
{
    @Resource
    private JdbcUtil jdbcUtil;

    @Override
    public FeatureType recongnizeFeatureType(String tableName, int colNum, String col_name) throws SQLException
    {
        String sql = String.format("select * from %s limit %d " , tableName , 1000);
        List<List<Object>> dataLists = jdbcUtil.findMoreResult(sql , null);

        if(colNum == 0 && col_name.toLowerCase().contains("id"))
        {
            return FeatureType.ID;
        }

        List<Object> groupList = groupBylist(dataLists , colNum);
        double threshhold = Math.sqrt((double)dataLists.size()) > 10 ? Math.sqrt((double) dataLists.size()) : 10;
        if(groupList != null && groupList.size() <= threshhold)
            return FeatureType.CATEGORY;

        for(List<Object> list : dataLists)
        {
            if(list.get(colNum) != null && !StringUtil.isNumeric(list.get(colNum).toString()))
                return FeatureType.TEXT;
        }
        return FeatureType.CONTINUOUS;
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
