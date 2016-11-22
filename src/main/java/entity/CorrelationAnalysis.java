package entity;

public class CorrelationAnalysis
{
    private TbColumn tb_col1;

    private TbColumn tb_col2;

    private double param_gradient;

    private double param_intercept;

    private double correlation;

    private double R2;

    private double SST;

    private double SSE;

    private double SSR;

    public TbColumn getTb_col1()
    {
        return tb_col1;
    }

    public void setTb_col1(TbColumn tb_col1)
    {
        this.tb_col1 = tb_col1;
    }

    public TbColumn getTb_col2()
    {
        return tb_col2;
    }

    public void setTb_col2(TbColumn tb_col2)
    {
        this.tb_col2 = tb_col2;
    }

    public double getParam_gradient()
    {
        return param_gradient;
    }

    public void setParam_gradient(double param_gradient)
    {
        this.param_gradient = param_gradient;
    }

    public double getParam_intercept()
    {
        return param_intercept;
    }

    public void setParam_intercept(double param_intercept)
    {
        this.param_intercept = param_intercept;
    }

    public double getCorrelation()
    {
        return correlation;
    }

    public void setCorrelation(double correlation)
    {
        this.correlation = correlation;
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

    public double getSST()
    {
        return SST;
    }

    public void setSST(double SST)
    {
        this.SST = SST;
    }

    public double getR2()
    {
        return R2;
    }

    public void setR2(double r2)
    {
        R2 = r2;
    }
}
