package rbac.entity;

import java.util.Date;

public class RbacRegUser
{
    private Integer id;
    private String reg_code;
    private String reg_name;
    private String reg_pwd;
    private Date reg_time;

    public RbacRegUser()
    {

    }
    public RbacRegUser(String reg_code , String reg_name , String reg_pwd , Date reg_time)
    {
        this.reg_code = reg_code;
        this.reg_name = reg_name;
        this.reg_pwd = reg_pwd;
        this.reg_time = reg_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReg_code() {
        return reg_code;
    }

    public void setReg_code(String reg_code) {
        this.reg_code = reg_code;
    }

    public String getReg_name() {
        return reg_name;
    }

    public void setReg_name(String reg_name) {
        this.reg_name = reg_name;
    }

    public String getReg_pwd() {
        return reg_pwd;
    }

    public void setReg_pwd(String reg_pwd) {
        this.reg_pwd = reg_pwd;
    }

    public Date getReg_time() {
        return reg_time;
    }

    public void setReg_time(Date reg_time) {
        this.reg_time = reg_time;
    }
}
