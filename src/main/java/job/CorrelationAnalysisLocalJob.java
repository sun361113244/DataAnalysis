package job;

import entity.CorrelationAnalysis;
import entity.DescriptiveStatistic;
import entity.FeatureType;
import entity.TbColumn;
import service.ColumnLocalService;
import service.CorrelationAnalysisLocalService;
import service.DescriptiveStatisticsLocalService;
import util.Config;
import util.JdbcUtil;
import util.SpringContextHolder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CorrelationAnalysisLocalJob implements AnalysisJob
{
    private double r2_upper = Config.GFI;

    private String tblName;

    private JdbcUtil jdbcUtil;

    private ColumnLocalService columnService;

    private DescriptiveStatisticsLocalService descriptiveStatisticsLocalService;

    private CorrelationAnalysisLocalService correlationAnalysisLocalService;

    public CorrelationAnalysisLocalJob(String tblName , double r2_upper)
    {
        this.tblName = tblName;
        this.r2_upper = r2_upper;
        jdbcUtil = SpringContextHolder.getBean("jdbcUtil");

        columnService = SpringContextHolder.getBean("columnLocalJdbcImpl");
        descriptiveStatisticsLocalService = SpringContextHolder.getBean("descriptiveStatisticsLocalJdbcImpl");
        correlationAnalysisLocalService = SpringContextHolder.getBean("correlationAnalysisLocalJdbcServiceImpl");
    }
    @Override
    public Map<String , Object> call() throws Exception
    {
        String sql = String.format("select * from %s " , tblName);
        List<List<Object>> dataLists = jdbcUtil.findMoreResult(sql , null);

        List<TbColumn> tbColumns = columnService.loadSchema(tblName, dataLists);
        List<DescriptiveStatistic> descriptiveStatistics = descriptiveStatisticsLocalService.selectDescriptiveStatistics(dataLists ,tbColumns);
        List<CorrelationAnalysis> correlationAnalysises = correlationAnalysisLocalService.selectSummaryInfo(dataLists , tbColumns);

        checkRegressionResult(tbColumns , descriptiveStatistics ,correlationAnalysises , r2_upper);

        Map<String , Object> resMap = new HashMap<>();
        resMap.put("descriptiveStatistics" , descriptiveStatistics);
        resMap.put("correlationAnalysises" , correlationAnalysises);
        return resMap;
    }

    private void checkRegressionResult(List<TbColumn> tbColumns, List<DescriptiveStatistic> descriptiveStatistics, List<CorrelationAnalysis> correlationAnalysises, double r2_upper)
    {
        Iterator<CorrelationAnalysis> iter = correlationAnalysises.iterator();
        while (iter.hasNext())
        {
            CorrelationAnalysis elem = iter.next();
            for(DescriptiveStatistic descriptiveStatistic : descriptiveStatistics)
            {
                if(descriptiveStatistic != null && elem.getTb_col1().getCol_num() == descriptiveStatistic.getTbCol().getCol_num() &&
                        elem.getTb_col1().getFeatureType() == FeatureType.CONTINUOUS)
                {
                    if(!((-8 * Math.sqrt((double)6/descriptiveStatistic.getN()) <= descriptiveStatistic.getSkewness()) &&
                            (8 * Math.sqrt((double)6/descriptiveStatistic.getN()) > descriptiveStatistic.getSkewness())) ||
                            !((-8 * Math.sqrt((double)24/descriptiveStatistic.getN()) <= descriptiveStatistic.getKurtosis()) &&
                                    (8 * Math.sqrt((double)24/descriptiveStatistic.getN()) > descriptiveStatistic.getKurtosis())) ||
                            (elem.getrSquare() < r2_upper))
                    {
                        iter.remove();
                    }
                }
            }
        }
    }
}
