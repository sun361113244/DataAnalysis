package rbac.service.impl;

import org.springframework.stereotype.Service;
import rbac.entity.RbacUri;
import rbac.mapper.RbacUserUriMapper;
import rbac.service.UserUriService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserUriServiceImpl implements UserUriService
{
    @Resource
    private RbacUserUriMapper rbacUserUriMapper;

    public List<RbacUri> selectMenuListByUserId(Integer id)
    {
        return rbacUserUriMapper.selectMenuListByUserId(id);
    }

    public List<RbacUri> selectUriListByUserId(Integer id)
    {
        return rbacUserUriMapper.selectUriListByUserId(id);
    }
}
