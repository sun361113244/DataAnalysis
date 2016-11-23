package entity;

public class CorrelationAnalysis
{
    private TbColumn tb_col1;

    private TbColumn tb_col2;

    private long n;

    private double intercept;

    private double interceptStdErr;

    private double slope;

    private double slopeStdErr;

    private double slopeConfidenceIntercal;

    private double r;

    private double rSquare;

    private double significance;

    private double meanSquareError;

    private double regressionSumSquare;

    private double sumofCrossProducts;

    private double sumSquaredErrors;

    private double totalSumSquares;

    private double xSumSquares;

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

    public long getN()
    {
        return n;
    }

    public void setN(long n)
    {
        this.n = n;
    }

    public double getIntercept()
    {
        return intercept;
    }

    public void setIntercept(double intercept)
    {
        this.intercept = intercept;
    }

    public double getInterceptStdErr()
    {
        return interceptStdErr;
    }

    public void setInterceptStdErr(double interceptStdErr)
    {
        this.interceptStdErr = interceptStdErr;
    }

    public double getSlope()
    {
        return slope;
    }

    public void setSlope(double slope)
    {
        this.slope = slope;
    }

    public double getSlopeStdErr()
    {
        return slopeStdErr;
    }

    public void setSlopeStdErr(double slopeStdErr)
    {
        this.slopeStdErr = slopeStdErr;
    }

    public double getSlopeConfidenceIntercal()
    {
        return slopeConfidenceIntercal;
    }

    public void setSlopeConfidenceIntercal(double slopeConfidenceIntercal)
    {
        this.slopeConfidenceIntercal = slopeConfidenceIntercal;
    }

    public double getR()
    {
        return r;
    }

    public void setR(double r)
    {
        this.r = r;
    }

    public double getrSquare()
    {
        return rSquare;
    }

    public void setrSquare(double rSquare)
    {
        this.rSquare = rSquare;
    }

    public double getSignificance()
    {
        return significance;
    }

    public void setSignificance(double significance)
    {
        this.significance = significance;
    }

    public double getMeanSquareError()
    {
        return meanSquareError;
    }

    public void setMeanSquareError(double meanSquareError)
    {
        this.meanSquareError = meanSquareError;
    }

    public double getRegressionSumSquare()
    {
        return regressionSumSquare;
    }

    public void setRegressionSumSquare(double regressionSumSquare)
    {
        this.regressionSumSquare = regressionSumSquare;
    }

    public double getSumofCrossProducts()
    {
        return sumofCrossProducts;
    }

    public void setSumofCrossProducts(double sumofCrossProducts)
    {
        this.sumofCrossProducts = sumofCrossProducts;
    }

    public double getSumSquaredErrors()
    {
        return sumSquaredErrors;
    }

    public void setSumSquaredErrors(double sumSquaredErrors)
    {
        this.sumSquaredErrors = sumSquaredErrors;
    }

    public double getTotalSumSquares()
    {
        return totalSumSquares;
    }

    public void setTotalSumSquares(double totalSumSquares)
    {
        this.totalSumSquares = totalSumSquares;
    }

    public double getxSumSquares()
    {
        return xSumSquares;
    }

    public void setxSumSquares(double xSumSquares)
    {
        this.xSumSquares = xSumSquares;
    }
}
