package analysis.entity;

import java.util.ArrayList;
import java.util.List;

public class CorrelationAnalysisVo
{
    private TbColumn tb_col1;

    private TbColumn tb_col2;

    private String n;

    private String intercept;

    private String interceptStdErr;

    private String slope;

    private String slopeStdErr;

    private String slopeConfidenceIntercal;

    private String r2;

    private String significance;

    private String meanSquareError;

    private String regressionSumSquare;

    private String sumofCrossProducts;

    private String sumSquaredErrors;

    private String totalSumSquares;

    private String xSumSquares;

    private String msg;

    private RegressionAnalysisEchartsVo regressionAnalysisEchartsVo;

    public CorrelationAnalysisVo(CorrelationAnalysis correlationAnalysis)
    {
        this.tb_col1 = correlationAnalysis.getTb_col1();
        this.tb_col2 = correlationAnalysis.getTb_col2();

        this.n = String.format("%d", correlationAnalysis.getN() == 0 ? 0 : correlationAnalysis.getN());
        this.intercept = String.format("%.2f", correlationAnalysis.getIntercept() == 0 ? 0 : correlationAnalysis.getIntercept());
        this.interceptStdErr = String.format("%.2f", correlationAnalysis.getInterceptStdErr() == 0 ? 0 : correlationAnalysis.getInterceptStdErr());
        this.slope = String.format("%.2f", correlationAnalysis.getSlope() == 0 ? 0 : correlationAnalysis.getSlope());
        this.slopeStdErr = String.format("%.2f", correlationAnalysis.getSlopeStdErr() == 0 ? 0 : correlationAnalysis.getSlopeStdErr());
        this.slopeConfidenceIntercal = String.format("%.2f", correlationAnalysis.getSlopeConfidenceIntercal() == 0 ? 0 : correlationAnalysis.getSlopeConfidenceIntercal());
        this.r2 = String.format("%.2f", correlationAnalysis.getrSquare() == 0 ? 0 : correlationAnalysis.getrSquare());
        this.significance = String.format("%.2f", correlationAnalysis.getSignificance() == 0 ? 0 : correlationAnalysis.getSignificance());
        this.meanSquareError = String.format("%.2f", correlationAnalysis.getMeanSquareError() == 0 ? 0 : correlationAnalysis.getMeanSquareError());
        this.regressionSumSquare = String.format("%.2f", correlationAnalysis.getRegressionSumSquare() == 0 ? 0 : correlationAnalysis.getRegressionSumSquare());
        this.sumofCrossProducts = String.format("%.2f", correlationAnalysis.getSumofCrossProducts() == 0 ? 0 : correlationAnalysis.getSumofCrossProducts());
        this.sumSquaredErrors = String.format("%.2f", correlationAnalysis.getSumSquaredErrors() == 0 ? 0 : correlationAnalysis.getSumSquaredErrors());
        this.totalSumSquares = String.format("%.2f", correlationAnalysis.getTotalSumSquares() == 0 ? 0 : correlationAnalysis.getTotalSumSquares());
        this.xSumSquares = String.format("%.2f", correlationAnalysis.getxSumSquares() == 0 ? 0 : correlationAnalysis.getxSumSquares());

        if(correlationAnalysis.getXyPoint() != null)
            this.regressionAnalysisEchartsVo = new RegressionAnalysisEchartsVo(correlationAnalysis);

        this.msg = String.format("\"%s\" 每增长 1 ,\"%s\" %s 约%.2f" ,
                correlationAnalysis.getTb_col1() != null && correlationAnalysis.getTb_col1().getCol_name() != null ?  correlationAnalysis.getTb_col1().getCol_name() : "",
                correlationAnalysis.getTb_col2() != null && correlationAnalysis.getTb_col2().getCol_name() != null ?  correlationAnalysis.getTb_col2().getCol_name() : "",
                correlationAnalysis.getSlope() >= 0 ? "增长" : "减少" ,
                Math.abs(correlationAnalysis.getSlope()));
    }

    public static List<CorrelationAnalysisVo> toVoList(List<CorrelationAnalysis> correlationAnalysises)
    {
        if(correlationAnalysises == null)
            return null;

        List<CorrelationAnalysisVo> correlationAnalysisVos = new ArrayList<>();
        for (CorrelationAnalysis correlationAnalysis : correlationAnalysises)
        {
            if(correlationAnalysis != null)
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

    public void setN(String n)
    {
        this.n = n;
    }

    public void setIntercept(String intercept)
    {
        this.intercept = intercept;
    }

    public void setInterceptStdErr(String interceptStdErr)
    {
        this.interceptStdErr = interceptStdErr;
    }

    public void setSlope(String slope)
    {
        this.slope = slope;
    }

    public void setSlopeStdErr(String slopeStdErr)
    {
        this.slopeStdErr = slopeStdErr;
    }

    public void setSlopeConfidenceIntercal(String slopeConfidenceIntercal)
    {
        this.slopeConfidenceIntercal = slopeConfidenceIntercal;
    }

    public void setR2(String r2)
    {
        this.r2 = r2;
    }

    public void setSignificance(String significance)
    {
        this.significance = significance;
    }

    public void setMeanSquareError(String meanSquareError)
    {
        this.meanSquareError = meanSquareError;
    }

    public void setRegressionSumSquare(String regressionSumSquare)
    {
        this.regressionSumSquare = regressionSumSquare;
    }

    public void setSumofCrossProducts(String sumofCrossProducts)
    {
        this.sumofCrossProducts = sumofCrossProducts;
    }

    public void setSumSquaredErrors(String sumSquaredErrors)
    {
        this.sumSquaredErrors = sumSquaredErrors;
    }

    public void setTotalSumSquares(String totalSumSquares)
    {
        this.totalSumSquares = totalSumSquares;
    }

    public void setxSumSquares(String xSumSquares)
    {
        this.xSumSquares = xSumSquares;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public RegressionAnalysisEchartsVo getRegressionAnalysisEchartsVo()
    {
        return regressionAnalysisEchartsVo;
    }

    public void setRegressionAnalysisEchartsVo(RegressionAnalysisEchartsVo regressionAnalysisEchartsVo)
    {
        this.regressionAnalysisEchartsVo = regressionAnalysisEchartsVo;
    }
}
