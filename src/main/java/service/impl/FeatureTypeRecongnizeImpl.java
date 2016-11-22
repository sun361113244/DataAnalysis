package service.impl;

import entity.FeatureType;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.RelationalGroupedDataset;
import org.apache.spark.sql.Row;
import org.springframework.stereotype.Service;
import service.FeatureTypeRecongnizeService;
import util.StringUtil;

import java.util.List;

@Service
public class FeatureTypeRecongnizeImpl implements FeatureTypeRecongnizeService
{
    final static int chkNum = 1000;

    final static int group_by_rec_num = 10;

    public FeatureType recongnizeFeatureType(Dataset<Row> jdbcDF, String name, int col_index)
    {
        Dataset<Row> colDt = jdbcDF.limit(chkNum);
        RelationalGroupedDataset groupedDataset = colDt.groupBy(name);

        if(groupedDataset.count().count() < group_by_rec_num)
            return FeatureType.CATEGORY;

        List<Row> colList = colDt.collectAsList();
        for(Row row : colList)
        {
            if(!row.isNullAt(col_index) && !StringUtil.isNumeric(row.get(col_index).toString()))
                return FeatureType.TEXT;
        }

        return FeatureType.CONTINUOUS;
    }
}
