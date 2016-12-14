import analysis.entity.AnalysisLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import analysis.service.LogService;
import util.SpringContextHolder;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml" , "classpath:spring-mybatis-rbac.xml"})
public class testMybatis
{
    @Test
    public void test1()
    {
        LogService logService = SpringContextHolder.getBean("logServiceMysqlImpl");

        AnalysisLog analysisLog = new AnalysisLog();
//        analysisLog.setId(1);
        analysisLog.setOp_userid("333");
        analysisLog.setOp_excu_time(new Date());
        analysisLog.setOp_res("-1");

        logService.writeLog(analysisLog);

    }
}
