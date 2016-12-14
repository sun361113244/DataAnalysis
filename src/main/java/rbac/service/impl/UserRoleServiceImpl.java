package rbac.service.impl;

import org.springframework.stereotype.Service;
import rbac.entity.RbacRole;
import rbac.mapper.RbacUserRoleMapper;
import rbac.service.UserRoleService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Csun on 2015-06-06.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService
{
    @Resource
    private RbacUserRoleMapper rbacUserRoleMapper;

    public List<RbacRole> selectRolesById(Integer id)
    {
        return rbacUserRoleMapper.selectRoleListByUserId(id);
    }

    public int insertUserRoles(Integer id, Integer[] nodes)
    {
        rbacUserRoleMapper.deleteByUserId(id);
        for (int i = 0; i<nodes.length;i++)
        {
            rbacUserRoleMapper.insertUserRoles(id, nodes[i]);
        }
        return 1;
    }
}
