package analysis.entity;

import sys.echarts.component.*;
import sys.echarts.component.part.EchartsScatterSeriesData;

import java.util.ArrayList;
import java.util.List;

public class RegressionAnalysisEchartsVo
{
    private EChartsGridBean grid;
    private EChartsScatterXAxisBean xAxis;
    private EChartsScatterYAxisBean yAxis;
    private List<EchartsScatterSeriesData> series;
    private EChartsTitleBean title;
    private EChartsToolTipBean tooltip;

    public RegressionAnalysisEchartsVo(CorrelationAnalysis correlationAnalysis)
    {
        this.grid = new EChartsGridBean();

        this.title = new EChartsTitleBean(String.format("%s 与 %s" , correlationAnalysis.getTb_col1().getCol_name() ,correlationAnalysis.getTb_col2().getCol_name()));

        this.tooltip = new EChartsToolTipBean();

        double tbxmax = Double.parseDouble(String.format("%.2f", correlationAnalysis.getXyPoint().get(0).getX() == 0 ? 0 : correlationAnalysis.getXyPoint().get(0).getX()));
        double tbxmin = Double.parseDouble(String.format("%.2f", correlationAnalysis.getXyPoint().get(0).getX() == 0 ? 0 : correlationAnalysis.getXyPoint().get(0).getX()));
        double tbymin = Double.parseDouble(String.format("%.2f", correlationAnalysis.getXyPoint().get(0).getY() == 0 ? 0 : correlationAnalysis.getXyPoint().get(0).getY()));
        double tbymax = Double.parseDouble(String.format("%.2f", correlationAnalysis.getXyPoint().get(0).getY() == 0 ? 0 : correlationAnalysis.getXyPoint().get(0).getY()));
        List<List<Double>> xydots = new ArrayList<>();
        for(XYPoint xyPoint : correlationAnalysis.getXyPoint())
        {
            List<Double> dot = new ArrayList<>();
            double x = Double.parseDouble(String.format("%.2f", xyPoint.getX() == 0 ? 0 : xyPoint.getX()));
            double y = Double.parseDouble(String.format("%.2f", xyPoint.getY() == 0 ? 0 : xyPoint.getY()));
            if(x > tbxmax)
                tbxmax = x;
            if(x < tbxmin)
                tbxmin = x;
            if(y > tbymax)
                tbymax = y;
            if(y < tbymin)
                tbymin = y;

            dot.add(Double.parseDouble(String.format("%.2f", xyPoint.getX() == 0 ? 0 : xyPoint.getX())));
            dot.add(Double.parseDouble(String.format("%.2f", xyPoint.getY() == 0 ? 0 : xyPoint.getY())));

            xydots.add(dot);
        }
        EchartsScatterSeriesData echartsScatterSeriesData = new EchartsScatterSeriesData();
        echartsScatterSeriesData.setType("scatter");
        echartsScatterSeriesData.setData(xydots);
        this.series = new ArrayList<>();
        this.series.add(echartsScatterSeriesData);

        double xinterval = tbxmax - tbxmin;
        this.xAxis = new EChartsScatterXAxisBean();
        this.xAxis.setMin(tbxmin - xinterval * 0.1);
        this.xAxis.setMax(tbxmax + xinterval * 0.1);

        double yinterval = tbymax - tbymin;
        this.yAxis = new EChartsScatterYAxisBean();
        this.yAxis.setMin(tbymin - yinterval * 0.1);
        this.yAxis.setMax(tbymax + yinterval * 0.1);
    }

    public EChartsGridBean getGrid()
    {
        return grid;
    }

    public void setGrid(EChartsGridBean grid)
    {
        this.grid = grid;
    }

    public EChartsScatterXAxisBean getxAxis()
    {
        return xAxis;
    }

    public void setxAxis(EChartsScatterXAxisBean xAxis)
    {
        this.xAxis = xAxis;
    }

    public EChartsScatterYAxisBean getyAxis()
    {
        return yAxis;
    }

    public void setyAxis(EChartsScatterYAxisBean yAxis)
    {
        this.yAxis = yAxis;
    }

    public List<EchartsScatterSeriesData> getSeries()
    {
        return series;
    }

    public void setSeries(List<EchartsScatterSeriesData> series)
    {
        this.series = series;
    }

    public EChartsTitleBean getTitle()
    {
        return title;
    }

    public void setTitle(EChartsTitleBean title)
    {
        this.title = title;
    }

    public EChartsToolTipBean getTooltip()
    {
        return tooltip;
    }

    public void setTooltip(EChartsToolTipBean tooltip)
    {
        this.tooltip = tooltip;
    }
}
