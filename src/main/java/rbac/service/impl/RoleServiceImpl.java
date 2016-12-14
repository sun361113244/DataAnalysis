package rbac.service.impl;

import org.springframework.stereotype.Service;
import rbac.entity.RbacRole;
import rbac.mapper.RbacRoleMapper;
import rbac.service.RoleService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService
{
    @Resource
    private RbacRoleMapper rbacRoleMapper;

    public List<RbacRole> selectAllRoles()
    {
        return rbacRoleMapper.selectAllRoles();
    }

    public int selectIsRoleNameExist(String name)
    {
        return rbacRoleMapper.selectIsRoleNameExist(name);
    }

    public int selectIsRoleNameExistExceptID(Integer id, String name)
    {
        return rbacRoleMapper.selectIsRoleNameExistExceptID(id, name);
    }

    public int insertRole(RbacRole role)
    {
        return rbacRoleMapper.insertRole(role);
    }

    public int deleteUserRoleById(Integer id)
    {
        RbacRole role = new RbacRole();
        role.setId(id);
        role.setStatus(2);
        return rbacRoleMapper.updateRoleById(role);
    }

    public int updateRoleById(RbacRole role)
    {
        return rbacRoleMapper.updateRoleById(role);
    }
}
