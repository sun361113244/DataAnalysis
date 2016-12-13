package api;

import com.alibaba.fastjson.JSON;
import entity.AnalysisFilter;
import entity.DescriptiveStatistic;
import entity.DescriptiveStatisticVo;
import job.DescriptiveStatisticLocalJob;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import util.Config;
import util.ExecutorContext;

import javax.annotation.Resource;
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

    @RequestMapping(value = "/summary")
    public ModelAndView summary( AnalysisFilter analysisFilter) throws Exception
    {
        ModelAndView mav = new ModelAndView("JsonView");

        DescriptiveStatisticLocalJob job = new DescriptiveStatisticLocalJob(analysisFilter);
        Future<Map<String , Object>> descriptiveStatistics = executorContext.submit(job);

        List<DescriptiveStatisticVo> descriptiveStatisticVos =
                DescriptiveStatisticVo.toVoList((List<DescriptiveStatistic>)descriptiveStatistics.get(Config.ANALYSE_TIMEOUT, TimeUnit.SECONDS).get("descriptiveStatistics"));

        mav.addObject("descriptiveAnalysis_summary" , descriptiveStatisticVos);
        mav.addObject("result" , 1);

        return mav;
    }
}
