package entity;

import java.util.Date;

public class AnalysisLog
{
    private Integer id;
    private String op_userid;
    private String op_ip;
    private String op_uri;
    private Date op_excu_time;
    private String op_params;
    private String op_res;
    private String op_exception;
    private Long op_consume_time;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getOp_userid()
    {
        return op_userid;
    }

    public void setOp_userid(String op_userid)
    {
        this.op_userid = op_userid;
    }

    public String getOp_ip()
    {
        return op_ip;
    }

    public void setOp_ip(String op_ip)
    {
        this.op_ip = op_ip;
    }

    public String getOp_uri()
    {
        return op_uri;
    }

    public void setOp_uri(String op_uri)
    {
        this.op_uri = op_uri;
    }

    public Date getOp_excu_time()
    {
        return op_excu_time;
    }

    public void setOp_excu_time(Date op_excu_time)
    {
        this.op_excu_time = op_excu_time;
    }

    public String getOp_params()
    {
        return op_params;
    }

    public void setOp_params(String op_params)
    {
        this.op_params = op_params;
    }

    public String getOp_res()
    {
        return op_res;
    }

    public void setOp_res(String op_res)
    {
        this.op_res = op_res;
    }

    public String getOp_exception()
    {
        return op_exception;
    }

    public void setOp_exception(String op_exception)
    {
        this.op_exception = op_exception;
    }

    public Long getOp_consume_time()
    {
        return op_consume_time;
    }

    public void setOp_consume_time(Long op_consume_time)
    {
        this.op_consume_time = op_consume_time;
    }
}
