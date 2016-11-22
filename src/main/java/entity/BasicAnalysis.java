package entity;

public class BasicAnalysis
{
    private TbColumn tb_col;

    private double mean;

    private double variance;

    private int numNonzeros;

    private double max;

    private double min;

    private double normL1;

    private double normL2;

    public TbColumn getTb_col()
    {
        return tb_col;
    }

    public void setTb_col(TbColumn tb_col)
    {
        this.tb_col = tb_col;
    }

    public double getMean()
    {
        return mean;
    }

    public void setMean(double mean)
    {
        this.mean = mean;
    }

    public double getVariance()
    {
        return variance;
    }

    public void setVariance(double variance)
    {
        this.variance = variance;
    }

    public int getNumNonzeros()
    {
        return numNonzeros;
    }

    public void setNumNonzeros(int numNonzeros)
    {
        this.numNonzeros = numNonzeros;
    }

    public double getMax()
    {
        return max;
    }

    public void setMax(double max)
    {
        this.max = max;
    }

    public double getMin()
    {
        return min;
    }

    public void setMin(double min)
    {
        this.min = min;
    }

    public double getNormL1()
    {
        return normL1;
    }

    public void setNormL1(double normL1)
    {
        this.normL1 = normL1;
    }

    public double getNormL2()
    {
        return normL2;
    }

    public void setNormL2(double normL2)
    {
        this.normL2 = normL2;
    }
}
