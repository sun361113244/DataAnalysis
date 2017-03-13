package analysis.entity;

import java.util.List;

public class EchartsPieSeriesData
{
    private String name;
    private String type;
    private List<EchartsSeriesData> data;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<EchartsSeriesData> getData()
    {
        return data;
    }

    public void setData(List<EchartsSeriesData> data)
    {
        this.data = data;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
