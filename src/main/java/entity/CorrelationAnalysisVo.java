package entity;

import java.util.ArrayList;
import java.util.List;

public class CorrelationAnalysisVo
{
    private TbColumn tb_col1;

    private TbColumn tb_col2;

    private String param_gradient;

    private String param_intercept;

    private String correlation;

    private String R2;

    private String SST;

    private String SSE;

    private String SSR;

    public CorrelationAnalysisVo(CorrelationAnalysis correlationAnalysis)
    {
        this.tb_col1 = correlationAnalysis.getTb_col1();
        this.tb_col2 = correlationAnalysis.getTb_col2();

        this.param_gradient = String.format("%.2f", correlationAnalysis.getParam_gradient() == 0 ? 0 : correlationAnalysis.getParam_gradient());
        this.param_intercept = String.format("%.2f", correlationAnalysis.getParam_intercept() == 0 ? 0 : correlationAnalysis.getParam_intercept());
        this.correlation = String.format("%.2f", correlationAnalysis.getCorrelation() == 0 ? 0 : correlationAnalysis.getCorrelation());
        this.R2 = String.format("%.2f", correlationAnalysis.getR2() == 0 ? 0 : correlationAnalysis.getR2());
        this.SST = String.format("%.2f", correlationAnalysis.getSST() == 0 ? 0 : correlationAnalysis.getSST());
        this.SSE = String.format("%.2f", correlationAnalysis.getSSE() == 0 ? 0 : correlationAnalysis.getSSE());
        this.SSR = String.format("%.2f", correlationAnalysis.getSSR() == 0 ? 0 : correlationAnalysis.getSSR());

    }

    public static List<CorrelationAnalysisVo> toVoList(List<CorrelationAnalysis> correlationAnalysises)
    {
        List<CorrelationAnalysisVo> correlationAnalysisVos = new ArrayList<CorrelationAnalysisVo>();
        for (CorrelationAnalysis correlationAnalysis : correlationAnalysises)
        {
            correlationAnalysisVos.add(new CorrelationAnalysisVo(correlationAnalysis));
        }
        return correlationAnalysisVos;
    }

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

    public String getParam_gradient()
    {
        return param_gradient;
    }

    public void setParam_gradient(String param_gradient)
    {
        this.param_gradient = param_gradient;
    }

    public String getParam_intercept()
    {
        return param_intercept;
    }

    public void setParam_intercept(String param_intercept)
    {
        this.param_intercept = param_intercept;
    }

    public String getCorrelation()
    {
        return correlation;
    }

    public void setCorrelation(String correlation)
    {
        this.correlation = correlation;
    }

    public String getR2()
    {
        return R2;
    }

    public void setR2(String r2)
    {
        R2 = r2;
    }

    public String getSST()
    {
        return SST;
    }

    public void setSST(String SST)
    {
        this.SST = SST;
    }

    public String getSSE()
    {
        return SSE;
    }

    public void setSSE(String SSE)
    {
        this.SSE = SSE;
    }

    public String getSSR()
    {
        return SSR;
    }

    public void setSSR(String SSR)
    {
        this.SSR = SSR;
    }
}
