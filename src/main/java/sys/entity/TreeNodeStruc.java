package sys.entity;

public abstract class TreeNodeStruc
{
    private Integer id;

    private Integer parentId;

    private String name;

    private boolean isLeaf;

    public TreeNodeStruc()
    {

    }

    public TreeNodeStruc(Integer id, Integer parentId , String nodeName)
    {
        this.id = id;
        this.parentId = parentId;
        this.name = nodeName;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getParentId()
    {
        return parentId;
    }

    public void setParentId(Integer parentId)
    {
        this.parentId = parentId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name == null ? null : name.trim();
    }

    public boolean isLeaf()
    {
        return isLeaf;
    }

    public void setLeaf(boolean leaf)
    {
        isLeaf = leaf;
    }
}
