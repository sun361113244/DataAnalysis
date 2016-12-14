package rbac.service;

import rbac.entity.RbacRole;

import java.util.List;

public interface RoleService
{
    List<RbacRole> selectAllRoles();

    int selectIsRoleNameExist(String name);

    int selectIsRoleNameExistExceptID(Integer id, String name);

    int insertRole(RbacRole role);

    int deleteUserRoleById(Integer id);

    int updateRoleById(RbacRole role);
}
