package service.impl;

import entity.FeatureType;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.RelationalGroupedDataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructField;
import org.springframework.stereotype.Service;
import service.FeatureTypeRecongnizeService;
import util.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class FeatureTypeRecongnizeImpl implements FeatureTypeRecongnizeService
{
    final static int chkNum = 1000;

    final static int group_by_rec_num = 10;

    public FeatureType recongnizeFeatureType(Dataset<Row> jdbcDF, StructField structField, int col_index)
    {
        Dataset<Row> colDt = jdbcDF.limit(chkNum);
//        List<Row> colList = colDt.collectAsList();

        if(isID(jdbcDF , structField , col_index))
            return FeatureType.ID;

        if(isCategory(jdbcDF , structField , col_index))
            return FeatureType.CATEGORY;

//        if(isDate(jdbcDF , structField , col_index))
//            return FeatureType.DATE;
//
//        if(isDatetime(jdbcDF , structField , col_index))
//            return FeatureType.DATETIME;

        if(isContinuous(jdbcDF , structField , col_index))
            return FeatureType.CONTINUOUS;


//        if(isWord(jdbcDF , structField , col_index))
//            return FeatureType.WORD;

        return FeatureType.TEXT;
    }

    private boolean isContinuous(Dataset<Row> jdbcDF, StructField structField, int col_index)
    {
        for(Row row : jdbcDF.collectAsList())
        {
            if(!row.isNullAt(col_index) && !StringUtil.isNumeric(row.get(col_index).toString()))
                return false;
        }
        return true;
    }

    private boolean isCategory(Dataset<Row> jdbcDF, StructField structField, int col_index)
    {
        RelationalGroupedDataset groupedDataset = jdbcDF.groupBy(structField.name());

        if(groupedDataset.count().count() < group_by_rec_num)
            return true;
        else
            return false;
    }

    private boolean isWord(Dataset<Row> jdbcDF, StructField structField, int col_index)
    {
        return false;
    }

    private boolean isDatetime(Dataset<Row> jdbcDF, StructField structField, int col_index)
    {
        if(structField.name().equals("datetime") || structField.name().equals("timestamp"))
            return true;

        return false;
    }

    private boolean isDate(Dataset<Row> jdbcDF, StructField structField, int col_index)
    {
        if(structField.name().equals("datetime") || structField.name().equals("timestamp"))
            return true;

        return false;
    }

    private boolean isID(Dataset<Row> jdbcDF, StructField structField, int col_index)
    {
        if(col_index == 0 && structField.name().toUpperCase().contains("id"))
            return true;
        else
            return false;
    }
}
