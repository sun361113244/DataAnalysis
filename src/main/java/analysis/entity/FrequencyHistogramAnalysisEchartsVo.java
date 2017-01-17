package analysis.entity;

import sys.echarts.component.*;
import sys.echarts.component.part.EchartsBarSeriesData;

import java.util.ArrayList;
import java.util.List;

public class FrequencyHistogramAnalysisEchartsVo
{
    private EChartsTitleBean title;
    private EChartsToolTipBean tooltip;
    private EchartsLegendBean legend;
    private EChartsBarXAxisBean xAxis;
    private EChartsBarYAxisBean yAxis;
    private List<EchartsBarSeriesData> series;

    public FrequencyHistogramAnalysisEchartsVo(FrequencyHistogramAnalysis frequencyHistogramAnalysis)
    {
        if(frequencyHistogramAnalysis.getFrequencyUnits() != null)
        {
            this.title = new EChartsTitleBean(frequencyHistogramAnalysis.getTbColumn().getCol_name());

            this.tooltip = new EChartsToolTipBean();

            this.legend = new EchartsLegendBean();
            List<String> legends = new ArrayList<>();
            legends.add("数量");
            this.legend.setData(legends);

            yAxis = new EChartsBarYAxisBean();

            String[] xAxisList = new String[frequencyHistogramAnalysis.getFrequencyUnits().size()];
            int[] yAxisList = new int[frequencyHistogramAnalysis.getFrequencyUnits().size()];

            int i = 0;
            for(FrequencyUnit frequencyUnit : frequencyHistogramAnalysis.getFrequencyUnits())
            {
                xAxisList[i] = String.format("%.2f", frequencyUnit.getUnitStart() == 0 ? 0 : frequencyUnit.getUnitStart());
                yAxisList[i] = frequencyUnit.getCount();
                i++;
            }
            this.xAxis = new EChartsBarXAxisBean();
            this.xAxis.setData(xAxisList);

            series = new ArrayList<>();
            EchartsBarSeriesData echartsBarSeriesData = new EchartsBarSeriesData();
            echartsBarSeriesData.setData(yAxisList);
            echartsBarSeriesData.setName("数量");
            echartsBarSeriesData.setType("bar");
            series.add(echartsBarSeriesData);
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

    public EchartsLegendBean getLegend()
    {
        return legend;
    }

    public void setLegend(EchartsLegendBean legend)
    {
        this.legend = legend;
    }

    public EChartsBarXAxisBean getxAxis()
    {
        return xAxis;
    }

    public void setxAxis(EChartsBarXAxisBean xAxis)
    {
        this.xAxis = xAxis;
    }

    public EChartsBarYAxisBean getyAxis()
    {
        return yAxis;
    }

    public void setyAxis(EChartsBarYAxisBean yAxis)
    {
        this.yAxis = yAxis;
    }

    public List<EchartsBarSeriesData> getSeries()
    {
        return series;
    }

    public void setSeries(List<EchartsBarSeriesData> series)
    {
        this.series = series;
    }
}
