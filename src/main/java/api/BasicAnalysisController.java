package api;

import com.alibaba.fastjson.JSON;
import entity.BasicAnalysis;
import entity.TbColumn;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.BasicAnalysisService;
import service.ColumnService;
import util.Config;
import util.SparkContextBean;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Controller
@RequestMapping("/BasicAnalysis")
public class BasicAnalysisController
{
    @Resource
    private ColumnService columnService;

    @Resource
    private BasicAnalysisService basicAnalysisService;

    @RequestMapping("/summary")
    public ModelAndView summary(String dBName , String tblName , String[] cols)
    {
        ModelAndView mav = new ModelAndView("JsonView");

        Dataset<Row> jdbcDF = loadDataSet(dBName , tblName);

        List<TbColumn> tbColumns = columnService.loadSchema(jdbcDF);

        List<BasicAnalysis> basicAnalysis = basicAnalysisService.selectSummaryInfo(dBName , tblName , tbColumns);

        mav.addObject("basicAnalysis_summary" , basicAnalysis);
        return mav;
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
