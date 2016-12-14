package rbac.mapper;

import rbac.entity.Area;

import java.util.List;

/**
 * Created by charles on 16/8/18.
 */
public interface AreaMapper extends BaseMapper<Area>
{

    public List<Area> getAreaByCityId(String id);
}