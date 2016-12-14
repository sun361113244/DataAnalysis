package rbac.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Date;
import java.util.List;

public class ActiveUser implements java.io.Serializable
{
    private Integer userid;                                     // 用户id（主键）
    private String usercode;                                    // 用户账号
    private String username;                                    // 用户名称

    private List<RbacUri> menus;                                 // 菜单
    private List<RbacUri> permissions;                          // 权限
    private List<RbacDep> departments;                          // 站点

    private String sessionId;                                   // 用户sessionId
    private Date srartTimestamp;                                // session起始时间
    private Date lastAccessTime;                                // 上次操作时间
    private Bool expired;                                       // 是否过期
    private String host;                                        // 主机名称

    public Integer getUserid()
    {
        return userid;
    }

    public void setUserid(Integer userid)
    {
        this.userid = userid;
    }

    public String getUsercode()
    {
        return usercode;
    }

    public void setUsercode(String usercode)
    {
        this.usercode = usercode;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public List<RbacUri> getMenus()
    {
        return menus;
    }

    public void setMenus(List<RbacUri> menus)
    {
        this.menus = menus;
    }

    public List<RbacUri> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(List<RbacUri> permissions)
    {
        this.permissions = permissions;
    }

    public List<RbacDep> getDepartments()
    {
        return departments;
    }

    public void setDepartments(List<RbacDep> departments)
    {
        this.departments = departments;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getSrartTimestamp() {
        return srartTimestamp;
    }

    public void setSrartTimestamp(Date srartTimestamp) {
        this.srartTimestamp = srartTimestamp;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public Bool getExpired() {
        return expired;
    }

    public void setExpired(Bool expired) {
        this.expired = expired;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}

