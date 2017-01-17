package analysis.entity;

import util.Config;

public class AnalysisFilter
{
    private String taskid;
    private String ip;
    private Integer port;
    private String user;
    private String pwd;
    private String db;
    private String schema;
    private String table;
    private String db_type;

    private int showFrequencyHistogramStat = 1;
    private double r2 = Config.GFI;

    public String getTaskid()
    {
        return taskid;
    }

    public void setTaskid(String taskid)
    {
        this.taskid = taskid;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public Integer getPort()
    {
        return port;
    }

    public void setPort(Integer port)
    {
        this.port = port;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getPwd()
    {
        return pwd;
    }

    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }

    public String getDb()
    {
        return db;
    }

    public void setDb(String db)
    {
        this.db = db;
    }

    public String getSchema()
    {
        return schema;
    }

    public void setSchema(String schema)
    {
        this.schema = schema;
    }

    public String getTable()
    {
        return table;
    }

    public void setTable(String table)
    {
        this.table = table;
    }

    public String getDb_type()
    {
        return db_type;
    }

    public void setDb_type(String db_type)
    {
        this.db_type = db_type;
    }

    public double getR2()
    {
        return r2;
    }

    public void setR2(double r2)
    {
        this.r2 = r2;
    }

    public int getShowFrequencyHistogramStat()
    {
        return showFrequencyHistogramStat;
    }

    public void setShowFrequencyHistogramStat(int showFrequencyHistogramStat)
    {
        this.showFrequencyHistogramStat = showFrequencyHistogramStat;
    }
}
