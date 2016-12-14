package rbac.mapper;

import org.apache.ibatis.annotations.Param;
import rbac.entity.RbacUri;

import java.util.List;

public interface RbacRoleUriMapper
{
    List<RbacUri> selectUriListByRoleId(@Param("id") Integer id);

    void deleteRoleUris(@Param("id") Integer id);

    void insertRoleUri(@Param("id") Integer id, @Param("node") Integer node);
}
