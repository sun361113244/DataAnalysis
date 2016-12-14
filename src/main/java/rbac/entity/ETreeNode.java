package rbac.entity;

public class ETreeNode
{
    private String id;
    private String text;
    private String state;
    private String iconCls;
    private ETreeNodeAttribute attributes;
    private ETreeNode[] children;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getIconCls()
    {
        return iconCls;
    }

    public void setIconCls(String iconCls)
    {
        this.iconCls = iconCls;
    }

    public ETreeNodeAttribute getAttributes()
    {
        return attributes;
    }

    public void setAttributes(ETreeNodeAttribute attributes)
    {
        this.attributes = attributes;
    }

    public ETreeNode[] getChildren()
    {
        return children;
    }

    public void setChildren(ETreeNode[] children)
    {
        this.children = children;
    }
}
