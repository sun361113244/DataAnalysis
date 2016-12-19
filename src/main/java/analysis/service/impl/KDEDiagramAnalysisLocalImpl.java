package analysis.service.impl;

import analysis.entity.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import analysis.service.ContinuousFeatureAnalysisService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Order(value = 3)
@Service
public class KDEDiagramAnalysisLocalImpl implements ContinuousFeatureAnalysisService
{
    @Override
    public void selectContinuousStatisticResult(DescriptiveStatistic descriptiveStatistic, List<List<Object>> rows, List<TbColumn> tbColumns, AnalysisFilter analysisFilter, int i)
    {
        double h = 3.5 * descriptiveStatistic.getStandardDeviation() / Math.sqrt(descriptiveStatistic.getN());

        KDEAnalysis kdeAnalysis = new KDEAnalysis();

        List<XYPoint> xyPoints = new ArrayList<>();
        for(List<Object> irow : rows)
        {
            if(irow.get(i) != null)
            {
                double xi = Double.parseDouble(irow.get(i).toString());
                XYPoint xyPoint = new XYPoint();
                xyPoint.setX(xi);

                double sum = 0;
                for(List<Object> jrow : rows)
                {
                    if(jrow.get(i) != null)
                    {
                        double x = Double.parseDouble(jrow.get(i).toString());

                        double kdeVal = 1.0 / Math.sqrt(2 * Math.PI) * Math.exp(-1.0 / 2.0 * Math.pow((x - xi)/h , 2));
                        sum += 1.0 / h * kdeVal / descriptiveStatistic.getN();
                    }
                }
                xyPoint.setY(sum);
                xyPoints.add(xyPoint);
            }
        }

        Collections.sort(xyPoints);
        kdeAnalysis.setTbColumn(tbColumns.get(i).clone());
        kdeAnalysis.setXyPoints(xyPoints);
        descriptiveStatistic.setKdeAnalysis(kdeAnalysis);
    }
}
