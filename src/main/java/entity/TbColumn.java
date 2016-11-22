package entity;

public class TbColumn implements Cloneable
{
    private int col_num;

    private String col_name;

    private String col_type;

    private boolean nullable;

    FeatureType featureType;

    public int getCol_num()
    {
        return col_num;
    }

    public void setCol_num(int col_num)
    {
        this.col_num = col_num;
    }

    public String getCol_name()
    {
        return col_name;
    }

    public void setCol_name(String col_name)
    {
        this.col_name = col_name;
    }

    public String getCol_type()
    {
        return col_type;
    }

    public void setCol_type(String col_type)
    {
        this.col_type = col_type;
    }

    public boolean isNullable()
    {
        return nullable;
    }

    public void setNullable(boolean nullable)
    {
        this.nullable = nullable;
    }

    public FeatureType getFeatureType()
    {
        return featureType;
    }

    public void setFeatureType(FeatureType featureType)
    {
        this.featureType = featureType;
    }

    public TbColumn clone()
    {
        TbColumn o = null;
        try
        {
            o = (TbColumn)super.clone();
        } catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        o.col_num = col_num;
        o.col_name = new String(this.col_name);
        o.col_type = new String(this.col_type);
        o.nullable = this.nullable;
        o.featureType = featureType;

        return o;
    }
}
