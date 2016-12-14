package rbac.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rbac.entity.RbacDep;
import rbac.mapper.RbacUserDepMapper;
import rbac.service.UserDepService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserDepServiceImpl implements UserDepService
{
    @Resource
    private RbacUserDepMapper rbacUserDepMapper;

    public List<RbacDep> selectDepListByUserId(Integer id)
    {
        return rbacUserDepMapper.selectDepListByUserId(id);
    }

    public int deleteByUserId(Integer id)
    {
        return rbacUserDepMapper.deleteByUserId(id);
    }

    public void insertUserDep(Integer id, Integer stationId)
    {
        rbacUserDepMapper.insertUserDep(id , stationId);
    }

    @Transactional
    public int insertUserDeps(Integer id, Integer[] nodes)
    {
        rbacUserDepMapper.deleteByUserId(id);
        for(int i = 0; i<nodes.length;i++)
        {
            rbacUserDepMapper.insertUserDep(id, nodes[i]);
        }
        return 1;
    }
}
