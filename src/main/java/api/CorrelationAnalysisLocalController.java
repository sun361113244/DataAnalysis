package api;

import entity.*;
import job.CorrelationAnalysisLocalJob;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import util.Config;
import util.ExecutorContext;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/CorrelationAnalysisLocal")
public class CorrelationAnalysisLocalController
{
    @Resource
    private ExecutorContext executorContext;

    private double r2_upper = Config.GFI;

    @RequestMapping("/summary")
    public ModelAndView summary(AnalysisFilter analysisFilter) throws Exception
    {
        ModelAndView mav = new ModelAndView("JsonView");

        CorrelationAnalysisLocalJob job = new CorrelationAnalysisLocalJob(analysisFilter);
        Future<Map<String , Object>> correlationAnalysis = executorContext.submit(job);

        Map<String , Object> furureRes = correlationAnalysis.get(Config.ANALYSE_TIMEOUT, TimeUnit.SECONDS);

        List<CorrelationAnalysisVo> correlationAnalysisesVos =
                CorrelationAnalysisVo.toVoList((List<CorrelationAnalysis>)furureRes.get("correlationAnalysises"));
        List<DescriptiveStatisticVo> descriptiveStatisticVos =
                DescriptiveStatisticVo.toVoList((List<DescriptiveStatistic>)furureRes.get("descriptiveStatistics"));

        mav.addObject("descriptiveAnalysis_summary" , descriptiveStatisticVos);
        mav.addObject("correlationAnalysis_summary" , correlationAnalysisesVos);
        mav.addObject("result" , 1);

        return mav;
    }
}
