package rbac.service.impl;

import org.springframework.stereotype.Service;
import rbac.entity.RbacDep;
import rbac.mapper.RbacDepMapper;
import rbac.service.DepService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepServiceImpl implements DepService
{
    @Resource
    private RbacDepMapper rbacDepMapper;

    public int selectIsDepCodeExist(String code)
    {
        return rbacDepMapper.selectIsDepCodeExist(code);
    }

    public int selectIsDepNameExist(String name)
    {
        return rbacDepMapper.selectIsDepNameExist(name);
    }

    public int selectIsDepCodeExistExceptID(Integer id, String code)
    {
        return rbacDepMapper.selectIsDepCodeExistExceptID(id, code);
    }

    public int selectIsDepNameExistExceptID(Integer id, String name)
    {
        return rbacDepMapper.selectIsDepNameExistExceptID(id , name);
    }

    public List<RbacDep> selectDepList()
    {
        return rbacDepMapper.selectDepList();
    }

    public int insertDep(RbacDep dep)
    {
        return rbacDepMapper.insertDep(dep);
    }

    public int updateDep(RbacDep dep)
    {
        return rbacDepMapper.updateDep(dep);
    }
}
