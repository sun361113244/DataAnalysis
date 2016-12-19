package analysis.entity;

import java.util.ArrayList;
import java.util.List;

public class FrequencyUnitVo
{
    private String unitStart;
    private String interval;
    private String count;
    private String ratio;

    public FrequencyUnitVo(FrequencyUnit frequencyUnit)
    {
        this.unitStart = String.format("%.2f", frequencyUnit.getUnitStart() == 0 ? 0 : frequencyUnit.getUnitStart());
        this.interval = String.format("%.2f", frequencyUnit.getInterval() == 0 ? 0 : frequencyUnit.getInterval());
        this.count = String.valueOf(frequencyUnit.getCount());
        this.ratio = String.format("%.2f", frequencyUnit.getRatio() == 0 ? 0 : frequencyUnit.getRatio());
    }

    public static List<FrequencyUnitVo> toVoList(List<FrequencyUnit> frequencyUnits)
    {
        if(frequencyUnits == null)
            return null;

        List<FrequencyUnitVo> frequencyUnitVos = new ArrayList<>();
        for (FrequencyUnit frequencyUnit : frequencyUnits)
        {
            if(frequencyUnit != null)
                frequencyUnitVos.add(new FrequencyUnitVo(frequencyUnit));
        }
        return frequencyUnitVos;
    }

    public String getUnitStart()
    {
        return unitStart;
    }

    public void setUnitStart(String unitStart)
    {
        this.unitStart = unitStart;
    }

    public String getInterval()
    {
        return interval;
    }

    public void setInterval(String interval)
    {
        this.interval = interval;
    }

    public String getCount()
    {
        return count;
    }

    public void setCount(String count)
    {
        this.count = count;
    }

    public String getRatio()
    {
        return ratio;
    }

    public void setRatio(String ratio)
    {
        this.ratio = ratio;
    }
}
