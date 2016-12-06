package job;

import entity.DescriptiveStatistic;
import entity.TbColumn;
import service.ColumnLocalService;
import service.DescriptiveStatisticsLocalService;
import util.JdbcUtil;
import util.SpringContextHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DescriptiveStatisticLocalJob implements AnalysisJob
{
    private String tblName;

    private JdbcUtil jdbcUtil;

    private ColumnLocalService columnService;

    private DescriptiveStatisticsLocalService descriptiveStatisticsLocalService;

    public DescriptiveStatisticLocalJob(String tblName)
    {
        this.tblName = tblName;

        jdbcUtil = SpringContextHolder.getBean("jdbcUtil");
        columnService = SpringContextHolder.getBean("columnLocalJdbcImpl");
        descriptiveStatisticsLocalService = SpringContextHolder.getBean("descriptiveStatisticsLocalJdbcImpl");
    }

    @Override
    public Map<String , Object> call() throws Exception
    {
        String sql = String.format("select * from %s " , tblName);
        List<List<Object>> dataLists = jdbcUtil.findMoreResult(sql , null);

        List<TbColumn> tbColumns = columnService.loadSchema(tblName , dataLists);
        List<DescriptiveStatistic> descriptiveStatistics = descriptiveStatisticsLocalService.selectDescriptiveStatistics(dataLists ,tbColumns);

        Map<String , Object> resMap = new HashMap<>();
        resMap.put("descriptiveStatistics" , descriptiveStatistics);
        return resMap;
    }
}
