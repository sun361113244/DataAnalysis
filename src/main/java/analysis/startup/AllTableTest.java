package analysis.startup;

import analysis.api.CorrelationAnalysisLocalController;

import javax.annotation.Resource;

public class AllTableTest
{
//    @Resource
//    private JdbcUtil jdbcUtil;

    @Resource
    private CorrelationAnalysisLocalController api;

    public void handler() throws Exception
    {
//        List<String> tblNames = jdbcUtil.selectAllTables();

//        analysis.api.summary("iris" , 0.6);
    }
}
