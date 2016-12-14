package rbac.entity;

import java.util.Date;

public class LoginUser
{
    private String usercode;                                    // 用户账号
    private String sessionId;                                   // 用户sessionId
    private Date srartTimestamp;                               // session起始时间
    private Date lastAccessTime;                               // 上次操作时间
    private boolean expired;                                       // 是否过期
    private String host;                                         // 主机名称

    public String getUsercode()
    {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean getExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public Date getSrartTimestamp() {
        return srartTimestamp;
    }

    public void setSrartTimestamp(Date srartTimestamp) {
        this.srartTimestamp = srartTimestamp;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
