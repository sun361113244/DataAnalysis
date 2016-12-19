package analysis.entity;

import java.util.List;

public class KDEAnalysis
{
    private TbColumn tbColumn;
    private List<XYPoint> xyPoints;
    private List<XYPoint> cdfPoints;

    public TbColumn getTbColumn()
    {
        return tbColumn;
    }

    public void setTbColumn(TbColumn tbColumn)
    {
        this.tbColumn = tbColumn;
    }

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
