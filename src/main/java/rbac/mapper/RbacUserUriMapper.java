package rbac.mapper;

import rbac.entity.RbacUri;

import java.util.List;


public interface RbacUserUriMapper
{
    List<RbacUri> selectMenuListByUserId(Integer id);

    List<RbacUri> selectUriListByUserId(Integer id);
}
