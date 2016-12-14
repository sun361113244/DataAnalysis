package rbac.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rbac.entity.RbacUri;
import rbac.mapper.RbacRoleUriMapper;
import rbac.service.RoleUriService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleUriServiceImpl implements RoleUriService
{
    @Resource
    private RbacRoleUriMapper rbacRoleUriMapper;

    public List<RbacUri> selectUriListByRoleId(Integer id)
    {
        return rbacRoleUriMapper.selectUriListByRoleId(id);
    }

    @Transactional
    public int insertRoleUris(Integer id, Integer[] nodes)
    {
        rbacRoleUriMapper.deleteRoleUris(id);
        for(int i = 0; i<nodes.length;i++)
        {
            rbacRoleUriMapper.insertRoleUri(id, nodes[i]);
        }
        return 1;
    }
}
