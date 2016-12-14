package analysis.service.impl;

import analysis.entity.CorrelationAnalysis;
import analysis.entity.TbColumn;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.stereotype.Service;
import analysis.service.CorrelationAnalysisService;
import analysis.service.RegressionAnalysis;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Service
public class CorrelationAnalysisSparkImpl implements CorrelationAnalysisService , Serializable
{
    @Resource(name = "RegressionAnalysisLocalImpl")
    private RegressionAnalysis regressionAnalysis;

    public List<CorrelationAnalysis> selectSummaryInfo(Dataset<Row> jdbcDF, final List<TbColumn> tbColumns, double r2_upper)
    {
        List<CorrelationAnalysis> correlationAnalysises = regressionAnalysis.analysis(jdbcDF.na().drop() , tbColumns , r2_upper);

        return correlationAnalysises;
    }
}
