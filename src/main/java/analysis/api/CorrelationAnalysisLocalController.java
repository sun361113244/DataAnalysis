package analysis.api;

import analysis.entity.*;
import analysis.job.CorrelationAnalysisLocalJob;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import util.Config;
import util.ExecutorContext;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/analysis/api/CorrelationAnalysisLocal")
public class CorrelationAnalysisLocalController
{
    @Resource
    private ExecutorContext executorContext;

    @RequestMapping("/summary")
    public ModelAndView summary(AnalysisFilter analysisFilter) throws Exception
    {
        ModelAndView mav = new ModelAndView("ApiReturnView");

        CorrelationAnalysisLocalJob job = new CorrelationAnalysisLocalJob(analysisFilter);
        Future<Map<String , Object>> correlationAnalysis = executorContext.submit(job);

        Map<String , Object> futureRes = correlationAnalysis.get(Config.ANALYSE_TIMEOUT, TimeUnit.SECONDS);

        List<DescriptiveStatisticVo> descriptiveStatisticVos =
                DescriptiveStatisticVo.toVoList((List<DescriptiveStatistic>)futureRes.get("descriptiveStatistics"));

        List<CorrelationAnalysisVo> correlationAnalysisesVos =
                CorrelationAnalysisVo.toVoList((List<CorrelationAnalysis>)futureRes.get("correlationAnalysises"));

        List<Object> datas = new ArrayList<>();
        datas.add(descriptiveStatisticVos);
        datas.add(correlationAnalysisesVos);
        List<String> controllerName = new ArrayList<>();
        controllerName.add("descriptiveAnalysis_summary");
        controllerName.add("correlationAnalysis_summary");

        ApiReturn apiReturn = new ApiReturn();
        apiReturn.setTaskid(analysisFilter.getTaskid());
        apiReturn.setReturn_code(0);
        apiReturn.setControllerName(controllerName);
        apiReturn.setData(datas);

        mav.addObject("apiReturn" , apiReturn);

        return mav;
    }
}
