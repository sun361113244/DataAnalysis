package api;

import entity.DescriptiveStatistic;
import entity.DescriptiveStatisticVo;
import job.DescriptiveStatisticLocalJob;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import util.Config;
import util.ExecutorContext;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/DescriptiveStatisticLocal")
public class DescriptiveStatisticLocalController
{
    @RequestMapping("/summary")
    public ModelAndView summary(String tblName) throws Exception
    {
        ModelAndView mav = new ModelAndView("JsonView");
        tblName = StringEscapeUtils.escapeSql(tblName);

        if(!isInputIllegal(tblName))
        {
            mav.addObject("result" , -1);
            mav.addObject("msg" , "输入参数不合法");
            return mav;
        }

        DescriptiveStatisticLocalJob job = new DescriptiveStatisticLocalJob(tblName);
        Future<Map<String , Object>> descriptiveStatistics = ExecutorContext.submit(job);

        List<DescriptiveStatisticVo> descriptiveStatisticVos =
                DescriptiveStatisticVo.toVoList((List<DescriptiveStatistic>)descriptiveStatistics.get(Config.ANALYSE_TIMEOUT, TimeUnit.SECONDS).get("descriptiveStatistics"));
        mav.addObject("descriptiveAnalysis_summary" , descriptiveStatisticVos);
        mav.addObject("result" , 1);

        return mav;
    }

    private boolean isInputIllegal(String tblName)
    {
        if(tblName == null || tblName.length() > 100 || tblName.contains(";") || tblName.contains("("))
            return false;

        return true;
    }
}
