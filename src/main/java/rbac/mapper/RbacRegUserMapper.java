package rbac.mapper;

import org.apache.ibatis.annotations.Param;
import rbac.entity.RbacRegUser;

import java.util.List;

public interface RbacRegUserMapper
{
    List<RbacRegUser> selectAllRegUsers();

    RbacRegUser selectRegUserByID(@Param("id") Integer id);

    int insertRegUser(RbacRegUser rbacRegUser);

    int deleteRegUserByID(@Param("id") Integer id);
}
