package analysis.entity;

import java.util.List;

public class FrequencyHistogramAnalysis
{
    private TbColumn tbColumn;

    private List<FrequencyUnit> frequencyUnits;

    private List<FrequencyUnit> cdfs;

    public TbColumn getTbColumn()
    {
        return tbColumn;
    }

    public void setTbColumn(TbColumn tbColumn)
    {
        this.tbColumn = tbColumn;
    }

    public List<FrequencyUnit> getFrequencyUnits()
    {
        return frequencyUnits;
    }

    public void setFrequencyUnits(List<FrequencyUnit> frequencyUnits)
    {
        this.frequencyUnits = frequencyUnits;
    }

    public List<FrequencyUnit> getCdfs()
    {
        return cdfs;
    }

    public void setCdfs(List<FrequencyUnit> cdfs)
    {
        this.cdfs = cdfs;
    }
}
