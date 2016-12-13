package entity;

public class FrequencyUnit implements Comparable<FrequencyUnit>
{
    private double unitStart;

    private double interval;

    private int count;

    private double ratio;

    public double getUnitStart()
    {
        return unitStart;
    }

    public void setUnitStart(double unitStart)
    {
        this.unitStart = unitStart;
    }

    public double getInterval()
    {
        return interval;
    }

    public void setInterval(double interval)
    {
        this.interval = interval;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public double getRatio()
    {
        return ratio;
    }

    public void setRatio(double ratio)
    {
        this.ratio = ratio;
    }

    @Override
    public int compareTo(FrequencyUnit o)
    {
        if(this.unitStart < o.getUnitStart())
            return -1;
        if(this.unitStart > o.getUnitStart())
            return 1;
        return 0;
    }
}
