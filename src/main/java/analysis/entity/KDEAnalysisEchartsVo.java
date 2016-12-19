package analysis.entity;

import sys.echarts.component.*;
import sys.echarts.component.part.EchartsScatterSeriesData;

import java.util.ArrayList;
import java.util.List;

public class KDEAnalysisEchartsVo
{
    private EChartsTitleBean title;
    private EChartsToolTipBean tooltip;
    private EChartsGridBean grid;
    private EChartsScatterXAxisBean xAxis;
    private EChartsScatterYAxisBean yAxis;
    private EchartsScatterSeriesData series;

    public KDEAnalysisEchartsVo(KDEAnalysis kdeAnalysis)
    {
        if(kdeAnalysis.getXyPoints() != null)
        {
            this.title = new EChartsTitleBean();
            this.title.setText(kdeAnalysis.getTbColumn().getCol_name());

            this.tooltip = new EChartsToolTipBean();

            this.grid = new EChartsGridBean();

            this.xAxis = new EChartsScatterXAxisBean();

            this.yAxis = new EChartsScatterYAxisBean();

            List<List<Double>> xydots = new ArrayList<>();
            int i = 0;
            for(XYPoint xyPoint : kdeAnalysis.getXyPoints())
            {
                List<Double> dot = new ArrayList<>();
                dot.add(Double.parseDouble(String.format("%.2f", xyPoint.getX() == 0 ? 0 : xyPoint.getX())));
                dot.add(Double.parseDouble(String.format("%.2f", xyPoint.getY() == 0 ? 0 : xyPoint.getY())));

                xydots.add(dot);
            }

            this.series = new EchartsScatterSeriesData();
            this.series.setType("scatter");
            this.series.setData(xydots);
        }
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

    public EchartsScatterSeriesData getSeries()
    {
        return series;
    }

    public void setSeries(EchartsScatterSeriesData series)
    {
        this.series = series;
    }
}
