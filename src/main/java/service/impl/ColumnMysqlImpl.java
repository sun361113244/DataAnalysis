package service.impl;

import entity.TbColumn;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.springframework.stereotype.Service;
import service.ColumnService;
import service.FeatureTypeRecongnizeService;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ColumnMysqlImpl implements ColumnService
{
    @Resource
    private FeatureTypeRecongnizeService featureTypeRecongnizeService;

    public List<TbColumn> loadSchema(Dataset<Row> jdbcDF)
    {
        List<TbColumn> tbColumns = new ArrayList<>();

        StructType structType = jdbcDF.schema();
        StructField[] structFields = structType.fields();

        int col_index = 0;
        for(StructField structField : structFields)
        {
            TbColumn tbColumn = new TbColumn();
            tbColumn.setCol_num(col_index);
            tbColumn.setCol_name(structField.name());
            tbColumn.setCol_type(structField.dataType().typeName());
            tbColumn.setNullable(structField.nullable());
            tbColumn.setFeatureType(featureTypeRecongnizeService.recongnizeFeatureType(jdbcDF , structField.name() , col_index));

            tbColumns.add(tbColumn);
            col_index++;
        }
        return tbColumns;
    }
}
