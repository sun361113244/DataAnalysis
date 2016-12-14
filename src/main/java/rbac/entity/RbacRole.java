package rbac.entity;

import java.util.Date;

public class RbacRole {
    private Integer id;

    private String name;

    private String description;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    public RbacRole()
    {

    }

    public RbacRole(Integer id, String name, String description , Date createTime , Date updateTime , Integer status)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof RbacRole)
        {
            RbacRole rbacRole = (RbacRole) obj;
            return this.id == rbacRole.getId();
        }
        return super.equals(obj);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}