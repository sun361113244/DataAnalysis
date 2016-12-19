package analysis.service.impl;

import analysis.entity.FeatureType;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.StructField;
import org.springframework.stereotype.Service;
import analysis.service.FeatureTypeRecongnizeLocalService;

import java.sql.SQLException;

@Service
public class FeatureTypeRecongnizeImpl
{
    final static int chkNum = 1000;

    final static int group_by_rec_num = 10;

    public FeatureType recongnizeFeatureType(Dataset<Row> jdbcDF, StructField structField, int col_index)
    {
        Dataset<Row> groupDt = jdbcDF.limit(chkNum);
        RelationalGroupedDataset groupedDataset = groupDt.groupBy(structField.name());

        if(groupedDataset.count().count() < group_by_rec_num)
            return FeatureType.CATEGORY;

        Dataset<String> colDt = groupDt.select(structField.name()).map(
                new MapFunction<Row,  String>()
                {
                    public String call(Row row) throws Exception
                    {
//                        if(!row.isNullAt(0) && !StringUtil.isNumeric(row.get(0).toString()))
//                            return "TEXT";
                        return null;
                    }
                } , Encoders.STRING()
        );

//        if(colDt.)
//        colDt.fi

//        List<Row> colList = colDt.collectAsList();
//        for(Row row : colList)
//        {
//            if(!row.isNullAt(col_index) && !StringUtil.isNumeric(row.get(col_index).toString()))
//                return FeatureType.TEXT;
//        }

        return FeatureType.CONTINUOUS;
    }

    public FeatureType recongnizeFeatureType(String tableName, int colNum, String col_name) throws SQLException
    {
        return null;
    }
}
