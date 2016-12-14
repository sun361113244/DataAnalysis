package rbac.entity;

import java.util.Date;

public class RbacUri extends TreeNodeStruc
{
    private String permission;

    private String uri;

    private String iconcls;

    private String treeCode;

    private Byte showType;

    private Byte sort;

    private Date createTime;

    private Date updateTime;

    public RbacUri()
    {
        super();

    }

    public RbacUri(Integer id, Integer parentId , String nodeName)
    {
        super(id , parentId , nodeName);
    }

    public RbacUri(Integer parentId, String uriName, String uriPermission, String uriLoc, String iconCls,
                   Byte showType, String treeCode, Byte sort , Date createTime, Date updateTime)
    {
        setParentId(parentId);
        setName(uriName);
        this.permission = uriPermission;
        this.uri = uriLoc;
        this.iconcls = iconCls;
        this.treeCode = treeCode;
        this.showType = showType;
        this.sort = sort;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public RbacUri(Integer id, Integer parentId, String uriName, String uriPermission, String uriLoc, String iconCls,
                   Byte showType, String treeCode, Byte sort, Date createTime, Date updateTime)
    {
        super(id , parentId , uriName);
        this.permission = uriPermission;
        this.uri = uriLoc;
        this.iconcls = iconCls;
        this.treeCode = treeCode;
        this.showType = showType;
        this.sort = sort;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
    public boolean equals(Object obj)
    {
        if (obj instanceof RbacUri)
        {
            RbacUri rbacUri = (RbacUri) obj;
            return this.getId() == rbacUri.getId();
        }
        return super.equals(obj);
    }

    public String getPermission()
    {
        return permission;
    }

    public void setPermission(String permission)
    {
        this.permission = permission == null ? null : permission.trim();
    }

    public String getUri()
    {
        return uri;
    }

    public void setUri(String uri)
    {
        this.uri = uri == null ? null : uri.trim();
    }

    public String getIconcls()
    {
        return iconcls;
    }

    public void setIconcls(String iconcls)
    {
        this.iconcls = iconcls == null ? null : iconcls.trim();
    }

    public String getTreeCode()
    {
        return treeCode;
    }

    public void setTreeCode(String treeCode)
    {
        this.treeCode = treeCode == null ? null : treeCode.trim();
    }

    public Byte getShowType()
    {
        return showType;
    }

    public void setShowType(Byte showType)
    {
        this.showType = showType;
    }

    public Byte getSort()
    {
        return sort;
    }

    public void setSort(Byte sort)
    {
        this.sort = sort;
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

}