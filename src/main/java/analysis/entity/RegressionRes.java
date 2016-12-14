package analysis.entity;

public class RegressionRes
{
    private double[] weights;

    private double R2;

    private double SST;

    private double SSE;

    private double SSR;

    public double getR2()
    {
        return R2;
    }

    public double[] getWeights()
    {
        return weights;
    }

    public void setWeights(double[] weights)
    {
        this.weights = weights;
    }

    public void setR2(double r2)
    {
        R2 = r2;
    }

    public double getSST()
    {
        return SST;
    }

    public void setSST(double SST)
    {
        this.SST = SST;
    }

    public double getSSE()
    {
        return SSE;
    }

    public void setSSE(double SSE)
    {
        this.SSE = SSE;
    }

    public double getSSR()
    {
        return SSR;
    }

    public void setSSR(double SSR)
    {
        this.SSR = SSR;
    }
}
