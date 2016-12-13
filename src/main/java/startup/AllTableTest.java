package startup;

import api.CorrelationAnalysisLocalController;
import org.springframework.stereotype.Component;
import util.JdbcUtil;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

public class AllTableTest
{
//    @Resource
//    private JdbcUtil jdbcUtil;

    @Resource
    private CorrelationAnalysisLocalController api;

    public void handler() throws Exception
    {
//        List<String> tblNames = jdbcUtil.selectAllTables();

        api.summary("iris" , 0.6);
    }
}
