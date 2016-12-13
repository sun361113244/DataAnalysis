package entity;

import java.util.List;

public class DescriptiveStatistic
{
    private TbColumn tbCol;

    private double min;

    private double max;

    private double mean;

    private double geometricMean;

    private long n;

    private double sum;

    private double sumOfSquares;

    private double standardDeviation;

    private double variance;

    private double skewness;

    private double kurtosis;

    private double quarter1;

    private double median;

    private double quarter3;

    private List<GroupRatio> groupRatios;

    private KDEAnalysis kdeAnalysis;

    private List<Double> exceptionVals;

    public TbColumn getTbCol()
    {
        return tbCol;
    }

    public void setTbCol(TbColumn tbCol)
    {
        this.tbCol = tbCol;
    }

    public double getMin()
    {
        return min;
    }

    public void setMin(double min)
    {
        this.min = min;
    }

    public double getMax()
    {
        return max;
    }

    public void setMax(double max)
    {
        this.max = max;
    }

    public double getMean()
    {
        return mean;
    }

    public void setMean(double mean)
    {
        this.mean = mean;
    }

    public double getGeometricMean()
    {
        return geometricMean;
    }

    public void setGeometricMean(double geometricMean)
    {
        this.geometricMean = geometricMean;
    }

    public long getN()
    {
        return n;
    }

    public void setN(long n)
    {
        this.n = n;
    }

    public double getSum()
    {
        return sum;
    }

    public void setSum(double sum)
    {
        this.sum = sum;
    }

    public double getSumOfSquares()
    {
        return sumOfSquares;
    }

    public void setSumOfSquares(double sumOfSquares)
    {
        this.sumOfSquares = sumOfSquares;
    }

    public double getStandardDeviation()
    {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation)
    {
        this.standardDeviation = standardDeviation;
    }

    public double getVariance()
    {
        return variance;
    }

    public void setVariance(double variance)
    {
        this.variance = variance;
    }

    public double getSkewness()
    {
        return skewness;
    }

    public void setSkewness(double skewness)
    {
        this.skewness = skewness;
    }

    public double getKurtosis()
    {
        return kurtosis;
    }

    public void setKurtosis(double kurtosis)
    {
        this.kurtosis = kurtosis;
    }

    public double getQuarter1()
    {
        return quarter1;
    }

    public void setQuarter1(double quarter1)
    {
        this.quarter1 = quarter1;
    }

    public double getMedian()
    {
        return median;
    }

    public void setMedian(double median)
    {
        this.median = median;
    }

    public double getQuarter3()
    {
        return quarter3;
    }

    public void setQuarter3(double quarter3)
    {
        this.quarter3 = quarter3;
    }

    public List<GroupRatio> getGroupRatios()
    {
        return groupRatios;
    }

    public void setGroupRatios(List<GroupRatio> groupRatios)
    {
        this.groupRatios = groupRatios;
    }

    public KDEAnalysis getKdeAnalysis()
    {
        return kdeAnalysis;
    }

    public void setKdeAnalysis(KDEAnalysis kdeAnalysis)
    {
        this.kdeAnalysis = kdeAnalysis;
    }

    public List<Double> getExceptionVals()
    {
        return exceptionVals;
    }

    public void setExceptionVals(List<Double> exceptionVals)
    {
        this.exceptionVals = exceptionVals;
    }
}
