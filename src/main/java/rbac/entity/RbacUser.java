package rbac.entity;

import java.util.Date;

public class RbacUser implements java.io.Serializable
{
    private Integer id;

    private String userName;

    private String userPasswrod;

    private String givenName;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    private Date loginTime;

    private Integer loginCount;

    public RbacUser()
    {

    }
    public RbacUser(Integer user_id, String user_name , String user_pwd , String given_name , Byte status , Date create_time ,
                    Date update_time , Date login_time , Integer login_count)
    {
        this.id = user_id;
        this.userName = user_name;
        this.userPasswrod = user_pwd;
        this.givenName = given_name;
        this.status = status;
        this.createTime = create_time;
        this.updateTime = update_time;
        this.loginTime = login_time;
        this.loginCount = login_count;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPasswrod()
    {
        return userPasswrod;
    }

    public void setUserPasswrod(String userPasswrod)
    {
        this.userPasswrod = userPasswrod == null ? null : userPasswrod.trim();
    }

    public String getGivenName()
    {
        return givenName;
    }

    public void setGivenName(String givenName)
    {
        this.givenName = givenName == null ? null : givenName.trim();
    }

    public Byte getStatus()
    {
        return status;
    }

    public void setStatus(Byte status)
    {
        this.status = status;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public Date getLoginTime()
    {

        return loginTime;
    }

    public void setLoginTime(Date loginTime)
    {
        this.loginTime = loginTime;
    }

    public Integer getLoginCount()
    {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount)
    {
        this.loginCount = loginCount;
    }
}
