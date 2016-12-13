package entity;

import java.util.List;

public class KDEAnalysis
{
    private List<XYPoint> xyPoints;
    private List<XYPoint> cdfPoints;

    public List<XYPoint> getXyPoints()
    {
        return xyPoints;
    }

    public void setXyPoints(List<XYPoint> xyPoints)
    {
        this.xyPoints = xyPoints;
    }

    public List<XYPoint> getCdfPoints()
    {
        return cdfPoints;
    }

    public void setCdfPoints(List<XYPoint> cdfPoints)
    {
        this.cdfPoints = cdfPoints;
    }
}
