package service.impl;

import entity.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Order(value = 4)
@Service
public class KDECDFDiagramAnalysisLocalImpl extends KDEDiagramAnalysisLocalImpl
{
    @Override
    public void selectContinuousStatisticResult(DescriptiveStatistic descriptiveStatistic, List<List<Object>> rows, List<TbColumn> tbColumns, AnalysisFilter analysisFilter, int i)
    {
        super.selectContinuousStatisticResult(descriptiveStatistic , rows , tbColumns , analysisFilter, i);

        List<XYPoint> cdfPoints = new ArrayList<>();
        if(descriptiveStatistic.getKdeAnalysis() != null &&
                descriptiveStatistic.getKdeAnalysis().getXyPoints() != null)
        {
            XYPoint cdfAnalysisFirst = new XYPoint();
            cdfAnalysisFirst.setX(descriptiveStatistic.getKdeAnalysis().getXyPoints().get(0).getX());
            cdfAnalysisFirst.setY(1.0 / descriptiveStatistic.getN());
            cdfPoints.add(cdfAnalysisFirst);

            int num = 1;
            for(int k = 1 ; k < descriptiveStatistic.getKdeAnalysis().getXyPoints().size();k++)
            {
                XYPoint current = descriptiveStatistic.getKdeAnalysis().getXyPoints().get(k);
                XYPoint last = descriptiveStatistic.getKdeAnalysis().getXyPoints().get(k -1);
                if(current.getX() == last.getX())
                {
                    cdfPoints.get(k - num).setY((double)(k+1) / descriptiveStatistic.getN());
                    num++;
                }
                else
                {
                    XYPoint cdfAnalysis = new XYPoint();
                    cdfAnalysis.setX(descriptiveStatistic.getKdeAnalysis().getXyPoints().get(k).getX());
                    cdfAnalysis.setY((double)(k + 1) / descriptiveStatistic.getN());
                    cdfPoints.add(cdfAnalysis);
                }
            }
        }
        descriptiveStatistic.getKdeAnalysis().setCdfPoints(cdfPoints);
    }
}

