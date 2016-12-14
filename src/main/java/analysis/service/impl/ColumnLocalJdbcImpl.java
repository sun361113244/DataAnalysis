package analysis.service.impl;

import analysis.entity.TbColumn;
import org.springframework.stereotype.Service;
import analysis.service.ColumnLocalService;
import analysis.service.FeatureTypeRecongnizeLocalService;
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
    public List<TbColumn> loadSchema(String tableName, List<List<Object>> dataLists) throws SQLException
    {
        List<TbColumn> tbColumns = jdbcUtil.findSchema(tableName);

        if(tbColumns == null)
            return null;

        for(TbColumn tbColumn : tbColumns)
        {
            tbColumn.setFeatureType(featureTypeRecongnizeService.recongnizeFeatureType(dataLists , tbColumn));
        }
        return tbColumns;
    }
}
