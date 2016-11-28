package api;

import com.alibaba.fastjson.JSON;
import entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.ColumnLocalService;
import service.CorrelationAnalysisLocalService;
import service.DescriptiveStatisticsLocalService;
import util.Config;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/CorrelationAnalysisLocal")
public class CorrelationAnalysisLocalController
{
    private double r2_upper = Config.GFI;

    @Resource
    private ColumnLocalService columnService;

    @Resource
    private DescriptiveStatisticsLocalService descriptiveStatisticsLocalService;

    @Resource
    private CorrelationAnalysisLocalService correlationAnalysisLocalService;

    @RequestMapping("/summary")
    public ModelAndView summary(String dBName, String tblName, Double r2, Integer step_start, Integer step_end)
    {
        ModelAndView mav = new ModelAndView("JsonView");

        if(!isInputIllegal(dBName, tblName , r2))
        {
            mav.addObject("result" , -1);
            mav.addObject("msg" , "输入参数不合法");
            return mav;
        }

        if(r2 != null)
            r2_upper= r2;

        List<TbColumn> tbColumns = null;
        List<DescriptiveStatistic> descriptiveStatistics = null;
        List<CorrelationAnalysis> correlationAnalysises = null;

        try
        {
            tbColumns = columnService.loadSchema(tblName);
            descriptiveStatistics = descriptiveStatisticsLocalService.selectDescriptiveStatistics(tblName ,tbColumns);
            correlationAnalysises = correlationAnalysisLocalService.selectSummaryInfo(tblName , tbColumns , r2_upper);

            checkRegressionResult(tbColumns , descriptiveStatistics ,correlationAnalysises);

        } catch (SQLException e)
        {
            mav.addObject("result" , -2);
            mav.addObject("msg" , "数据库操作错误");
            return mav;
        }

        List<CorrelationAnalysisVo> correlationAnalysisesVos = CorrelationAnalysisVo.toVoList(correlationAnalysises);
        List<DescriptiveStatisticVo> descriptiveStatisticVos = DescriptiveStatisticVo.toVoList(descriptiveStatistics);

        mav.addObject("descriptiveAnalysis_summary" , descriptiveStatisticVos);
        mav.addObject("correlationAnalysis_summary" , correlationAnalysisesVos);
        mav.addObject("result" , 1);

        System.out.println(JSON.toJSONString(descriptiveStatistics));
        return mav;
    }

    private void checkRegressionResult(List<TbColumn> tbColumns, List<DescriptiveStatistic> descriptiveStatistics, List<CorrelationAnalysis> correlationAnalysises)
    {
        Iterator<CorrelationAnalysis> iter = correlationAnalysises.iterator();
        while (iter.hasNext())
        {
            CorrelationAnalysis elem = iter.next();
            for(DescriptiveStatistic descriptiveStatistic : descriptiveStatistics)
            {
                if(elem.getTb_col1().getCol_num() == descriptiveStatistic.getTbCol().getCol_num() &&
                        elem.getTb_col1().getFeatureType() == FeatureType.CONTINUOUS)
                {
                    System.out.println("--------------------------------------------");
                    System.out.println(elem.getTb_col1().getCol_num());
                    System.out.println(-8 * Math.sqrt((double)6/descriptiveStatistic.getN()));
                    System.out.println(descriptiveStatistic.getSkewness());
                    System.out.println(descriptiveStatistic.getKurtosis());

                    System.out.println(((-8 * Math.sqrt(6/descriptiveStatistic.getN()) <= descriptiveStatistic.getSkewness()) &&
                            (8 * Math.sqrt(6/descriptiveStatistic.getN()) >= descriptiveStatistic.getSkewness())) );
                    System.out.println(((-8 * Math.sqrt(24/descriptiveStatistic.getN()) <= descriptiveStatistic.getKurtosis()) &&
                            (8 * Math.sqrt(24/descriptiveStatistic.getN()) >= descriptiveStatistic.getKurtosis())) );
                    System.out.println((elem.getrSquare() < Config.GFI));

                    if(!((-8 * Math.sqrt((double)6/descriptiveStatistic.getN()) <= descriptiveStatistic.getSkewness()) &&
                            (8 * Math.sqrt((double)6/descriptiveStatistic.getN()) > descriptiveStatistic.getSkewness())) ||
                            !((-8 * Math.sqrt((double)24/descriptiveStatistic.getN()) <= descriptiveStatistic.getKurtosis()) &&
                            (8 * Math.sqrt((double)24/descriptiveStatistic.getN()) > descriptiveStatistic.getKurtosis())) ||
                            (elem.getrSquare() < Config.GFI))
                    {
                        iter.remove();
                    }
                }
            }
        }
    }


    private boolean isInputIllegal(String dBName, String tblName, Double r2)
    {
        if(r2 != null && (r2 < 0 || r2 > 1))
            return false;
        return true;
    }
}
