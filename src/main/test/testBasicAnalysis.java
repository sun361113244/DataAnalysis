import api.BasicAnalysisController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import util.SpringContextHolder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml" })
public class testBasicAnalysis
{
    @Test
    public void test1()
    {
        BasicAnalysisController basicAnalysisController = SpringContextHolder.getBean("basicAnalysisController");

        basicAnalysisController.summary(null , "iris" , null);
    }
}
