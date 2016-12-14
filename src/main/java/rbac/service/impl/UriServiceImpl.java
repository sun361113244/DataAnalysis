package rbac.service.impl;

import org.springframework.stereotype.Service;
import rbac.entity.RbacUri;
import rbac.mapper.RbacUriMapper;
import rbac.service.UriService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UriServiceImpl implements UriService
{
    @Resource
    private RbacUriMapper rbacUriMapper;

    public List<RbacUri> selectUriList()
    {
        return rbacUriMapper.selectUriList();
    }

    public int insertUri(RbacUri rbacUri)
    {
        return rbacUriMapper.insertUri(rbacUri);
    }

    public int updateUri(RbacUri rbacUri)
    {
        return rbacUriMapper.updateUri(rbacUri);
    }

    public int deleteUriById(Integer id)
    {
        return rbacUriMapper.deleteUriById(id);
    }
}
