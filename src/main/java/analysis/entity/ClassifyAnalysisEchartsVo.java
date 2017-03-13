package analysis.entity;

import sys.echarts.component.EChartsTitleBean;
import sys.echarts.component.EChartsToolTipBean;
import sys.echarts.component.EchartsLegendBean;
import sys.echarts.component.part.EchartsBarSeriesData;

import java.util.ArrayList;
import java.util.List;

public class ClassifyAnalysisEchartsVo
{
    private EChartsTitleBean title;
    private EChartsToolTipBean tooltip;
    private EchartsLegendBean legend;
    private EchartsPieSeriesData series;

    public ClassifyAnalysisEchartsVo(List<GroupRatio> groupRatios)
    {
        if(groupRatios.size() > 0)
        {
            this.title = new EChartsTitleBean("aaa");

            this.tooltip = new EChartsToolTipBean();

            this.legend = new EchartsLegendBean();
            this.legend.setLeft("right");
            List<String> legends = new ArrayList<>();

            List<EchartsSeriesData> echartsPieSeriesDatas = new ArrayList<>();
            for(GroupRatio elem : groupRatios)
            {
                legends.add(elem.getCol_name());

                EchartsSeriesData data = new EchartsSeriesData();
                data.setName(elem.getCol_name());
                data.setValue(elem.getCount());
                echartsPieSeriesDatas.add(data);
            }
            this.legend.setData(legends);

            this.series = new EchartsPieSeriesData();
            this.series.setName("分类");
            this.series.setType("pie");
            this.series.setData(echartsPieSeriesDatas);
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

    public EchartsLegendBean getLegend()
    {
        return legend;
    }

    public void setLegend(EchartsLegendBean legend)
    {
        this.legend = legend;
    }

    public EChartsToolTipBean getTooltip()
    {
        return tooltip;
    }

    public void setTooltip(EChartsToolTipBean tooltip)
    {
        this.tooltip = tooltip;
    }

    public EchartsPieSeriesData getSeries()
    {
        return series;
    }

    public void setSeries(EchartsPieSeriesData series)
    {
        this.series = series;
    }
}
