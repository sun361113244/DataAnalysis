package sys.entity;

public class zTreeNode
{
    private Integer id;

    private Integer pId;

    private String name;

    private Boolean open;

    private Boolean checked;

    public zTreeNode(Integer node_id , Integer node_pid, String node_name, Boolean node_open, Boolean node_checked)
    {
        this.id = node_id;
        this.pId = node_pid;
        this.name = node_name;
        this.open = node_open;
        this.checked = node_checked;
    }
    public boolean equals(Object obj)
    {
        if (obj instanceof zTreeNode)
        {
            zTreeNode treeNode = (zTreeNode) obj;
            return this.id.equals(treeNode.getId());
        }
        return super.equals(obj);
    }
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getPId()
    {
        return pId;
    }

    public void setPId(Integer pId)
    {
        this.pId = pId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Boolean getOpen()
    {
        return open;
    }

    public void setOpen(Boolean open)
    {
        this.open = open;
    }

    public Boolean getChecked()
    {
        return checked;
    }

    public void setChecked(Boolean checked)
    {
        this.checked = checked;
    }
}
