package api;

import entity.DescriptiveStatistic;
import entity.DescriptiveStatisticVo;
import job.DescriptiveStatisticLocalJob;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import util.Config;
import util.ExecutorContext;
import util.JdbcUtil;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/DescriptiveStatisticLocal")
public class DescriptiveStatisticLocalController
{
    @Resource
    private ExecutorContext executorContext;

    @RequestMapping("/summary")
    public ModelAndView summary(@RequestParam("tblName") String tblName) throws Exception
    {
        ModelAndView mav = new ModelAndView("JsonView");
        tblName = StringEscapeUtils.escapeSql(tblName);

        DescriptiveStatisticLocalJob job = new DescriptiveStatisticLocalJob(tblName);
        Future<Map<String , Object>> descriptiveStatistics = executorContext.submit(job);

        List<DescriptiveStatisticVo> descriptiveStatisticVos =
                DescriptiveStatisticVo.toVoList((List<DescriptiveStatistic>)descriptiveStatistics.get(Config.ANALYSE_TIMEOUT, TimeUnit.SECONDS).get("descriptiveStatistics"));
        mav.addObject("descriptiveAnalysis_summary" , descriptiveStatisticVos);
        mav.addObject("result" , 1);

        return mav;
    }
}
