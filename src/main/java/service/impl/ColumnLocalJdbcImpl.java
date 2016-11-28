package service.impl;

import entity.TbColumn;
import org.springframework.stereotype.Service;
import service.ColumnLocalService;
import service.FeatureTypeRecongnizeLocalService;
import util.JdbcUtil;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Service
public class ColumnLocalJdbcImpl implements ColumnLocalService
{
    @Resource
    private JdbcUtil jdbcUtil;

    @Resource
    private FeatureTypeRecongnizeLocalService featureTypeRecongnizeService;

    @Override
    public List<TbColumn> loadSchema(String tableName) throws SQLException
    {
        List<TbColumn> tbColumns = jdbcUtil.findSchema(tableName);

        for(TbColumn tbColumn : tbColumns)
        {
            tbColumn.setFeatureType(featureTypeRecongnizeService.recongnizeFeatureType(tableName , tbColumn.getCol_num() , tbColumn.getCol_name()));
        }
        return tbColumns;
    }
}
