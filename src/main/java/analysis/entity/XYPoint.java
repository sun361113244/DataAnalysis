package analysis.entity;

public class XYPoint implements Comparable<XYPoint>
{
    private double x;
    private double y;

    public XYPoint()
    {

    }

    public XYPoint(double x , double y)
    {
        this.x = x;
        this.y = y;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    @Override
    public String toString()
    {
        return x + "\t" + y;
    }

    @Override
    public int compareTo(XYPoint o)
    {
        if(this.x < o.getX())
            return -1;
        if(this.x > o.getX())
            return 1;
        return 0;
    }
}
