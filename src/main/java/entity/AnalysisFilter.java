package entity;

import util.Config;

public class AnalysisFilter
{
    private String tblName;
    private int showFrequencyHistogramStat = 1;
    private int showKDEStat = 0;

    private double r2 = Config.GFI;

    public String getTblName()
    {
        return tblName;
    }

    public void setTblName(String tblName)
    {
        this.tblName = tblName;
    }

    public int getShowFrequencyHistogramStat()
    {
        return showFrequencyHistogramStat;
    }

    public void setShowFrequencyHistogramStat(int showFrequencyHistogramStat)
    {
        this.showFrequencyHistogramStat = showFrequencyHistogramStat;
    }

    public int getShowKDEStat()
    {
        return showKDEStat;
    }

    public void setShowKDEStat(int showKDEStat)
    {
        this.showKDEStat = showKDEStat;
    }

    public double getR2()
    {
        return r2;
    }

    public void setR2(double r2)
    {
        this.r2 = r2;
    }
}
