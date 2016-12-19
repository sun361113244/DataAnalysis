package sys.echarts.component.part;

import java.util.List;

public class EchartsScatterSeriesData extends EchartsSeriesData
{
    private List<List<Double>> data;

    public List<List<Double>> getData()
    {
        return data;
    }

    public void setData(List<List<Double>> data)
    {
        this.data = data;
    }
}
