package api;

import entity.*;
import job.CorrelationAnalysisLocalJob;
import org.apache.commons.lang.StringEscapeUtils;
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
    public ModelAndView summary(String tblName, Double r2) throws Exception
    {
        ModelAndView mav = new ModelAndView("JsonView");

        tblName = StringEscapeUtils.escapeSql(tblName);
        if(!isInputIllegal(tblName , r2))
        {
            mav.addObject("result" , -1);
            mav.addObject("msg" , "输入参数不合法");
            return mav;
        }

        if(r2 != null)
            r2_upper= r2;

        CorrelationAnalysisLocalJob job = new CorrelationAnalysisLocalJob(tblName , r2_upper);
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

    private boolean isInputIllegal(String tblName, Double r2)
    {
        if(r2 != null && (r2 < 0 || r2 > 1))
            return false;

        if(tblName == null || tblName.length() > 100 || tblName.contains(";") || tblName.contains("("))
            return false;

        return true;
    }
}
