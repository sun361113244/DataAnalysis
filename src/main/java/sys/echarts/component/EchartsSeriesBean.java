package sys.echarts.component;

import sys.echarts.component.part.EchartsSeriesData;

public class EchartsSeriesBean
{
    private String type;
    private EchartsSeriesData[] data;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public EchartsSeriesData[] getData()
    {
        return data;
    }

    public void setData(EchartsSeriesData[] data)
    {
        this.data = data;
    }
}
