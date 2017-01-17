package analysis.job;

import analysis.entity.AnalysisFilter;
import analysis.entity.DescriptiveStatistic;
import analysis.entity.TbColumn;
import analysis.service.ColumnLocalService;
import analysis.service.DescriptiveStatisticsLocalService;
import util.JdbcUtil;
import util.SpringContextHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DescriptiveStatisticLocalJob implements AnalysisJob
{
    private AnalysisFilter analysisFilter;

    private JdbcUtil jdbcUtil;

    private ColumnLocalService columnService;

    private DescriptiveStatisticsLocalService descriptiveStatisticsLocalService;

    public DescriptiveStatisticLocalJob(AnalysisFilter analysisFilter)
    {
        this.analysisFilter = analysisFilter;

        jdbcUtil = SpringContextHolder.getBean("jdbcUtil");
        columnService = SpringContextHolder.getBean("columnLocalJdbcImpl");
        descriptiveStatisticsLocalService = SpringContextHolder.getBean("descriptiveStatisticsLocalJdbcImpl");
    }

    @Override
    public Map<String , Object> call() throws Exception
    {
        String sql = String.format("select * from %s " , analysisFilter.getTable());
        List<List<Object>> dataLists = jdbcUtil.findMoreResult(sql , null);

        List<TbColumn> tbColumns = columnService.loadSchema(analysisFilter.getTable() , dataLists);
        List<DescriptiveStatistic> descriptiveStatistics = descriptiveStatisticsLocalService.selectDescriptiveStatistics(dataLists ,tbColumns ,analysisFilter);

        Map<String , Object> resMap = new HashMap<>();
        resMap.put("descriptiveStatistics" , descriptiveStatistics);
        return resMap;
    }
}
