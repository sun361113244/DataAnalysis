package api;

import com.alibaba.fastjson.JSON;
import entity.CorrelationAnalysis;
import entity.CorrelationAnalysisVo;
import entity.TbColumn;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.ColumnService;
import service.CorrelationAnalysisService;
import util.Config;
import util.SparkContextBean;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/CorrelationAnalysis")
public class CorrelationAnalysisController
{
    private double r2_upper = Config.GFI;

    @Resource
    private ColumnService columnService;

    @Resource
    private CorrelationAnalysisService correlationAnalysisService;

    @RequestMapping("/summaryLocal")
    public ModelAndView summaryLocal(String dBName, String tblName, Double r2, Integer step_start, Integer step_end)
    {
        ModelAndView mav = new ModelAndView("JsonView");

        if(!isInputIllegal(dBName, tblName , r2))
        {
            mav.addObject("result" , -1);
            return mav;
        }

        if(r2 != null)
            r2_upper= r2;

        Dataset<Row> jdbcDF = loadDataSet(dBName , tblName);

        List<TbColumn> tbColumns = columnService.loadSchema(jdbcDF);
//
//        List<CorrelationAnalysis> correlationAnalysises = correlationAnalysisService.selectSummaryInfo(jdbcDF , tbColumns , r2_upper);
//
//        System.out.println(JSON.toJSONString(correlationAnalysises));
//        List<CorrelationAnalysisVo> correlationAnalysisesVos = CorrelationAnalysisVo.toVoList(correlationAnalysises);
//
//        mav.addObject("correlationAnalysis_summary" , correlationAnalysisesVos);
//        mav.addObject("result" , 1);

        return mav;
    }

    @RequestMapping("/summary")
    public ModelAndView summary(String dBName, String tblName, Double r2, Integer step_start, Integer step_end)
    {
        ModelAndView mav = new ModelAndView("JsonView");

        if(!isInputIllegal(dBName, tblName , r2))
        {
            mav.addObject("result" , -1);
            return mav;
        }

        if(r2 != null)
            r2_upper= r2;

        Dataset<Row> jdbcDF = loadDataSet(dBName , tblName);

        List<TbColumn> tbColumns = columnService.loadSchema(jdbcDF);

        List<CorrelationAnalysis> correlationAnalysises = correlationAnalysisService.selectSummaryInfo(jdbcDF , tbColumns , r2_upper);

        System.out.println(JSON.toJSONString(correlationAnalysises));
        List<CorrelationAnalysisVo> correlationAnalysisesVos = CorrelationAnalysisVo.toVoList(correlationAnalysises);

        mav.addObject("correlationAnalysis_summary" , correlationAnalysisesVos);
        mav.addObject("result" , 1);

        return mav;
    }

    private boolean isInputIllegal(String dBName, String tblName, Double r2)
    {
        if(r2 != null && (r2 < 0 || r2 > 1))
            return false;
        return true;
    }

    private Dataset<Row> loadDataSet(String dBName, String tblName)
    {
        Dataset<Row> jdbcDF = SparkContextBean.getSparkSession().read()
                .format("jdbc")
                .option("driver" , Config.JDBCDRIVERCLASSNAME)
                .option("url", Config.JDBCURL)
                .option("fetchSize" , Config.JDBCFETCHSIZE)
                .option("dbtable", tblName)
                .option("user", Config.JDBCUSERNAME)
                .option("password", Config.JDBCPASSWORD)
                .load();
        return jdbcDF;
    }
}
