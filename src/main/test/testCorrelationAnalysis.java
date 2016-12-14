import analysis.entity.DescriptiveStatisticVo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring.xml" })
public class testCorrelationAnalysis
{
    @Test
    public void test1()
    {
        List<DescriptiveStatisticVo> descriptiveStatisticVos = new ArrayList<>();

        descriptiveStatisticVos.add(null);
        descriptiveStatisticVos.add(null);
        descriptiveStatisticVos.add(null);
        descriptiveStatisticVos.add(null);
        descriptiveStatisticVos.add(null);

        for(DescriptiveStatisticVo descriptiveStatisticVo : descriptiveStatisticVos)
        {
            System.out.println(descriptiveStatisticVo);
        }
        System.out.println(descriptiveStatisticVos.size());

    }
}
