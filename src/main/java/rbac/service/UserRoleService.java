package rbac.service;

import rbac.entity.RbacRole;

import java.util.List;

/**
 * Created by Csun on 2015-06-06.
 */
public interface UserRoleService
{
    List<RbacRole> selectRolesById(Integer id);

    int insertUserRoles(Integer id, Integer[] nodes);
}
