package analysis.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/CorrelationAnalysisSpark")
public class CorrelationAnalysisSparkController
{
    @RequestMapping("/summary")
    public ModelAndView summary(String dBName, String tblName, Double r2, Integer step_start, Integer step_end)
    {
        ModelAndView mav = new ModelAndView("JsonView");

//        if(!isInputIllegal(dBName, tblName , r2))
//        {
//            mav.addObject("result" , -1);
//            return mav;
//        }
//
//        if(r2 != null)
//            r2_upper= r2;
//
//        Dataset<Row> jdbcDF = loadDataSet(dBName , tblName);
//
//        List<TbColumn> tbColumns = columnService.loadSchema(jdbcDF);
//
//        List<CorrelationAnalysis> correlationAnalysises = correlationAnalysisService.selectSummaryInfo(jdbcDF , tbColumns , r2_upper);
//
//        List<CorrelationAnalysisVo> correlationAnalysisesVos = CorrelationAnalysisVo.toVoList(correlationAnalysises);
//
//        mav.addObject("correlationAnalysis_summary" , correlationAnalysisesVos);
//        mav.addObject("result" , 1);

        return mav;
    }

    private boolean isInputIllegal(String dBName, String tblName, Double r2)
    {
        if(r2 != null && (r2 < 0 || r2 > 1))
            return false;
        return true;
    }

//    private Dataset<Row> loadDataSet(String dBName, String tblName)
//    {
//        Dataset<Row> jdbcDF = SparkContextBean.getSparkSession().read()
//                .format("jdbc")
//                .option("driver" , Config.JDBCDRIVERCLASSNAME)
//                .option("url", Config.JDBCURL)
//                .option("fetchSize" , Config.JDBCFETCHSIZE)
//                .option("dbtable", tblName)
//                .option("user", Config.JDBCUSERNAME)
//                .option("password", Config.JDBCPASSWORD)
//                .load();
//        return jdbcDF;
//    }
}
