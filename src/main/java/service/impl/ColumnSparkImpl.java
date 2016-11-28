package service.impl;

import entity.TbColumn;
import org.springframework.stereotype.Service;
import service.FeatureTypeRecongnizeLocalService;


import javax.annotation.Resource;
import java.util.List;

@Service
public class ColumnSparkImpl
{
    @Resource
    private FeatureTypeRecongnizeLocalService featureTypeRecongnizeService;

    public List<TbColumn> loadSchema(String tableName)
    {

        return null;
//        List<TbColumn> tbColumns = new ArrayList<>();
//
//        StructType structType = jdbcDF.schema();
//        StructField[] structFields = structType.fields();
//
//        int col_index = 0;
//        for(StructField structField : structFields)
//        {
//            TbColumn tbColumn = new TbColumn();
//            tbColumn.setCol_num(col_index);
//            tbColumn.setCol_name(structField.name());
//            tbColumn.setCol_type(structField.dataType().typeName());
//            tbColumn.setNullable(structField.nullable());
//            tbColumn.setFeatureType(featureTypeRecongnizeService.recongnizeFeatureType(jdbcDF , structField , col_index));
//
//            tbColumns.add(tbColumn);
//            col_index++;
//        }
//        return tbColumns;
    }

}
