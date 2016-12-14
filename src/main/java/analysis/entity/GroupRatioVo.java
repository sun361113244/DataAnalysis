package analysis.entity;

import java.util.ArrayList;
import java.util.List;

public class GroupRatioVo
{
    private String col_name;

    private String count;

    private String ratio;

    public GroupRatioVo(GroupRatio groupRatio)
    {
        this.col_name = groupRatio.getCol_name();
        this.count = String.format("%d", groupRatio.getCount() == 0 ? 0 : groupRatio.getCount());
        this.ratio = String.format("%.2f", groupRatio.getRatio() == 0 ? 0 : groupRatio.getRatio() * 100);

    }

    public String getCol_name()
    {
        return col_name;
    }

    public void setCol_name(String col_name)
    {
        this.col_name = col_name;
    }

    public String getCount()
    {
        return count;
    }

    public void setCount(String count)
    {
        this.count = count;
    }

    public String getRatio()
    {
        return ratio;
    }

    public void setRatio(String ratio)
    {
        this.ratio = ratio;
    }

    public static List<GroupRatioVo> toVolist(List<GroupRatio> groupRatios)
    {
        if(groupRatios == null)
            return null;

        List<GroupRatioVo> groupRatioVos = new ArrayList<>();
        for(GroupRatio groupRatio : groupRatios)
        {
            if(groupRatio != null)
                groupRatioVos.add(new GroupRatioVo(groupRatio));
        }
        return groupRatioVos;
    }
}
