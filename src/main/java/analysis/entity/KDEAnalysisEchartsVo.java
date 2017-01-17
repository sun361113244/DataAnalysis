package analysis.entity;

import sys.echarts.component.*;
import sys.echarts.component.part.EchartsScatterSeriesData;

import java.util.ArrayList;
import java.util.List;

public class KDEAnalysisEchartsVo
{
    private EChartsGridBean grid;
    private EchartsLegendBean legend;
    private EChartsScatterXAxisBean xAxis;
    private EChartsScatterYAxisBean yAxis;
    private List<EchartsScatterSeriesData> series;
    private EChartsTitleBean title;
    private EChartsToolTipBean tooltip;

    public KDEAnalysisEchartsVo(KDEAnalysis kdeAnalysis)
    {
        if(kdeAnalysis.getXyPoints() != null || kdeAnalysis.getCdfPoints() !=null)
        {
            this.title = new EChartsTitleBean(kdeAnalysis.getTbColumn().getCol_name());
            this.tooltip = new EChartsToolTipBean();
            this.grid = new EChartsGridBean();
            this.xAxis = new EChartsScatterXAxisBean();

            this.yAxis = new EChartsScatterYAxisBean();

            List<String> legends = new ArrayList<>();
            if(kdeAnalysis.getXyPoints() != null)
            {
                legends.add("KDE");
            }
            if(kdeAnalysis.getCdfPoints() !=null)
                legends.add("CDF");
            this.legend = new EchartsLegendBean();
            this.legend.setData(legends);

            this.series = new ArrayList<>();
            if(kdeAnalysis.getXyPoints() != null)
            {
                int i = 0;
                List<List<Double>> xydots = new ArrayList<>();
                for(XYPoint xyPoint : kdeAnalysis.getXyPoints())
                {
                    List<Double> dot = new ArrayList<>();
                    dot.add(Double.parseDouble(String.format("%.2f", xyPoint.getX() == 0 ? 0 : xyPoint.getX())));
                    dot.add(Double.parseDouble(String.format("%.2f", xyPoint.getY() == 0 ? 0 : xyPoint.getY())));

                    xydots.add(dot);
                }
                EchartsScatterSeriesData echartsScatterSeriesData = new EchartsScatterSeriesData();
                echartsScatterSeriesData.setType("scatter");
                echartsScatterSeriesData.setName("KDE");
                echartsScatterSeriesData.setData(xydots);
                this.series.add(echartsScatterSeriesData);
            }
            if(kdeAnalysis.getCdfPoints() != null)
            {
                int i = 0;
                List<List<Double>> xydots = new ArrayList<>();
                for(XYPoint xyPoint : kdeAnalysis.getCdfPoints())
                {
                    List<Double> dot = new ArrayList<>();
                    dot.add(Double.parseDouble(String.format("%.2f", xyPoint.getX() == 0 ? 0 : xyPoint.getX())));
                    dot.add(Double.parseDouble(String.format("%.2f", xyPoint.getY() == 0 ? 0 : xyPoint.getY())));

                    xydots.add(dot);
                }
                EchartsScatterSeriesData echartsScatterSeriesData = new EchartsScatterSeriesData();
                echartsScatterSeriesData.setType("scatter");
                echartsScatterSeriesData.setName("CDF");
                echartsScatterSeriesData.setData(xydots);
                this.series.add(echartsScatterSeriesData);
            }
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

    public List<EchartsScatterSeriesData> getSeries()
    {
        return series;
    }

    public void setSeries(List<EchartsScatterSeriesData> series)
    {
        this.series = series;
    }

    public EchartsLegendBean getLegend()
    {
        return legend;
    }

    public void setLegend(EchartsLegendBean legend)
    {
        this.legend = legend;
    }
}
