package analysis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DescriptiveStatisticVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    private TbColumn tbCol;
    private String min;
    private String max;
    private String mean;
    private String geometricMean;
    private String n;
    private String sum;
    private String sumOfSquares;
    private String standardDeviation;
    private String variance;
    private String percentiles;
    private String skewness;
    private String kurtosis;
    private String median;
    private List<GroupRatioVo> groupRatios;
    private FrequencyHistogramAnalysisVo frequencyHistogramAnalysis;
    private FrequencyHistogramAnalysisEchartsVo frequencyHistogramAnalysisEchartsVo;
    private KDEAnalysisEchartsVo kdeAnalysisEchartsVo;
    private List<ExceptionValueVo> exceptionVals;
    private String msg;

    public DescriptiveStatisticVo(DescriptiveStatistic descriptiveStatistic)
    {
        this.tbCol = descriptiveStatistic.getTbCol();
        this.min = String.format("%.2f", descriptiveStatistic.getMin() == 0 ? 0 : descriptiveStatistic.getMin());
        this.max = String.format("%.2f", descriptiveStatistic.getMax() == 0 ? 0 : descriptiveStatistic.getMax());
        this.mean = String.format("%.2f", descriptiveStatistic.getMean() == 0 ? 0 : descriptiveStatistic.getMean());
        this.geometricMean = String.format("%.2f", descriptiveStatistic.getGeometricMean() == 0 ? 0 : descriptiveStatistic.getGeometricMean());
        this.n = String.format("%d", descriptiveStatistic.getN() == 0 ? 0 : descriptiveStatistic.getN());
        this.sum = String.format("%.2f", descriptiveStatistic.getSum() == 0 ? 0 : descriptiveStatistic.getSum());
        this.sumOfSquares = String.format("%.2f", descriptiveStatistic.getSumOfSquares() == 0 ? 0 : descriptiveStatistic.getSumOfSquares());
        this.standardDeviation = String.format("%.2f", descriptiveStatistic.getStandardDeviation() == 0 ? 0 : descriptiveStatistic.getStandardDeviation());
        this.variance =String.format("%.2f", descriptiveStatistic.getVariance() == 0 ? 0 : descriptiveStatistic.getVariance());
        this.skewness = String.format("%.2f", descriptiveStatistic.getSkewness() == 0 ? 0 : descriptiveStatistic.getSkewness());
        this.kurtosis = String.format("%.2f", descriptiveStatistic.getKurtosis() == 0 ? 0 : descriptiveStatistic.getKurtosis());
        this.median = String.format("%.2f", descriptiveStatistic.getMedian() == 0 ? 0 : descriptiveStatistic.getMedian());

        if(descriptiveStatistic.getGroupRatios() != null)
            this.groupRatios = GroupRatioVo.toVolist(descriptiveStatistic.getGroupRatios());

        if(descriptiveStatistic.getFrequencyHistogramAnalysis() != null)
            this.frequencyHistogramAnalysis = new FrequencyHistogramAnalysisVo(descriptiveStatistic.getFrequencyHistogramAnalysis());

        if(descriptiveStatistic.getFrequencyHistogramAnalysis() != null)
            this.frequencyHistogramAnalysisEchartsVo = new FrequencyHistogramAnalysisEchartsVo(descriptiveStatistic.getFrequencyHistogramAnalysis());

        if(descriptiveStatistic.getKdeAnalysis() != null)
            this.kdeAnalysisEchartsVo = new KDEAnalysisEchartsVo(descriptiveStatistic.getKdeAnalysis());

        if(descriptiveStatistic.getExceptionValues() != null)
        {
            this.exceptionVals = new ArrayList<>();
            for(ExceptionValue val : descriptiveStatistic.getExceptionValues())
            {
                ExceptionValueVo exceptionValueVo = new ExceptionValueVo();
                exceptionValueVo.setVal(String.format("%.2f", val.getValue() == 0 ? 0 : val.getValue()));
                exceptionValueVo.setExceptionStat(val.getExceptionStat());

                this.exceptionVals.add(exceptionValueVo);
            }
        }

        if(descriptiveStatistic.getTbCol().getFeatureType() == FeatureType.CONTINUOUS)
        {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("范围是:%s ~ %s." , this.min , this.max ));
            if(descriptiveStatistic.getSkewness() >= -2 * Math.sqrt((double)6/descriptiveStatistic.getN()) &&
                    descriptiveStatistic.getSkewness() <= -2 * Math.sqrt((double)6/descriptiveStatistic.getN()) &&
                    descriptiveStatistic.getKurtosis() >= -2 * Math.sqrt((double)24/descriptiveStatistic.getN()) &&
                    descriptiveStatistic.getKurtosis() <= -2 * Math.sqrt((double)24/descriptiveStatistic.getN()))
            {
                sb.append(String.format("数据符合均值%s ,方差%s正态分布 ." ,this.getMean() , this.variance ));
            }
            if(descriptiveStatistic.getSkewness() < -1 * Math.sqrt((double)6/descriptiveStatistic.getN()))
                sb.append(String.format("大多数值在高处.中位数是%s." ,this.getMedian() ));
            if(descriptiveStatistic.getSkewness() > 1 * Math.sqrt((double)6/descriptiveStatistic.getN()))
                sb.append(String.format("大多数值在低处.中位数是%s." ,this.getMedian() ));
            if(this.exceptionVals!= null && this.exceptionVals.size() != 0)
            {
                sb.append("低位异常值:");
                for(int i = 0; i < this.exceptionVals.size(); i++)
                {
                    if(this.exceptionVals.get(i).getExceptionStat() < 0)
                        sb.append(this.exceptionVals.get(i).getVal() + ",");
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append(" 高位异常值:");
                for(int i = 0; i < this.exceptionVals.size(); i++)
                {
                    if(this.exceptionVals.get(i).getExceptionStat() > 0)
                        sb.append(this.exceptionVals.get(i).getVal() + ",");
                }
                sb.deleteCharAt(sb.length() - 1);
            }

            this.msg = sb.toString();
        }
    }

    public static List<DescriptiveStatisticVo> toVoList(List<DescriptiveStatistic> descriptiveStatistics)
    {
        if(descriptiveStatistics == null)
            return null;

        List<DescriptiveStatisticVo> descriptiveStatisticVos = new ArrayList<>();
        for (DescriptiveStatistic descriptiveStatistic : descriptiveStatistics)
        {
            if(descriptiveStatistic != null)
                descriptiveStatisticVos.add(new DescriptiveStatisticVo(descriptiveStatistic));
        }
        return descriptiveStatisticVos;
    }

    public TbColumn getTbCol()
    {
        return tbCol;
    }

    public void setTbCol(TbColumn tbCol)
    {
        this.tbCol = tbCol;
    }

    public String getMin()
    {
        return min;
    }

    public void setMin(String min)
    {
        this.min = min;
    }

    public String getMean()
    {
        return mean;
    }

    public void setMean(String mean)
    {
        this.mean = mean;
    }

    public String getMax()
    {
        return max;
    }

    public void setMax(String max)
    {
        this.max = max;
    }

    public String getGeometricMean()
    {
        return geometricMean;
    }

    public void setGeometricMean(String geometricMean)
    {
        this.geometricMean = geometricMean;
    }

    public String getN()
    {
        return n;
    }

    public void setN(String n)
    {
        this.n = n;
    }

    public String getSum()
    {
        return sum;
    }

    public void setSum(String sum)
    {
        this.sum = sum;
    }

    public String getSumOfSquares()
    {
        return sumOfSquares;
    }

    public void setSumOfSquares(String sumOfSquares)
    {
        this.sumOfSquares = sumOfSquares;
    }

    public String getStandardDeviation()
    {
        return standardDeviation;
    }

    public void setStandardDeviation(String standardDeviation)
    {
        this.standardDeviation = standardDeviation;
    }

    public String getVariance()
    {
        return variance;
    }

    public void setVariance(String variance)
    {
        this.variance = variance;
    }

    public String getPercentiles()
    {
        return percentiles;
    }

    public void setPercentiles(String percentiles)
    {
        this.percentiles = percentiles;
    }

    public String getKurtosis()
    {
        return kurtosis;
    }

    public void setKurtosis(String kurtosis)
    {
        this.kurtosis = kurtosis;
    }

    public String getMedian()
    {
        return median;
    }

    public void setMedian(String median)
    {
        this.median = median;
    }

    public List<GroupRatioVo> getGroupRatios()
    {
        return groupRatios;
    }

    public void setGroupRatios(List<GroupRatioVo> groupRatios)
    {
        this.groupRatios = groupRatios;
    }

    public String getSkewness()
    {
        return skewness;
    }

    public void setSkewness(String skewness)
    {
        this.skewness = skewness;
    }

    public FrequencyHistogramAnalysisVo getFrequencyHistogramAnalysis()
    {
        return frequencyHistogramAnalysis;
    }

    public void setFrequencyHistogramAnalysis(FrequencyHistogramAnalysisVo frequencyHistogramAnalysis)
    {
        this.frequencyHistogramAnalysis = frequencyHistogramAnalysis;
    }

    public FrequencyHistogramAnalysisEchartsVo getFrequencyHistogramAnalysisEchartsVo()
    {
        return frequencyHistogramAnalysisEchartsVo;
    }

    public void setFrequencyHistogramAnalysisEchartsVo(FrequencyHistogramAnalysisEchartsVo frequencyHistogramAnalysisEchartsVo)
    {
        this.frequencyHistogramAnalysisEchartsVo = frequencyHistogramAnalysisEchartsVo;
    }

    public KDEAnalysisEchartsVo getKdeAnalysisEchartsVo()
    {
        return kdeAnalysisEchartsVo;
    }

    public void setKdeAnalysisEchartsVo(KDEAnalysisEchartsVo kdeAnalysisEchartsVo)
    {
        this.kdeAnalysisEchartsVo = kdeAnalysisEchartsVo;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }
}
