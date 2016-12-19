package analysis.entity;

import java.util.List;

public class FrequencyHistogramAnalysisVo
{
    private List<FrequencyUnitVo> frequencyUnits;

    private List<FrequencyUnitVo> cdfs;

    public FrequencyHistogramAnalysisVo(FrequencyHistogramAnalysis frequencyHistogramAnalysis)
    {
        if(frequencyHistogramAnalysis.getFrequencyUnits() != null)
            this.frequencyUnits = FrequencyUnitVo.toVoList(frequencyHistogramAnalysis.getFrequencyUnits());

        if(frequencyHistogramAnalysis.getCdfs() != null)
            this.cdfs = FrequencyUnitVo.toVoList(frequencyHistogramAnalysis.getCdfs());
    }

    public List<FrequencyUnitVo> getFrequencyUnits()
    {
        return frequencyUnits;
    }

    public void setFrequencyUnits(List<FrequencyUnitVo> frequencyUnits)
    {
        this.frequencyUnits = frequencyUnits;
    }

    public List<FrequencyUnitVo> getCdfs()
    {
        return cdfs;
    }

    public void setCdfs(List<FrequencyUnitVo> cdfs)
    {
        this.cdfs = cdfs;
    }
}
