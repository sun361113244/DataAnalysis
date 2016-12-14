package rbac.service;

import rbac.entity.RbacUri;

import java.util.List;

public interface UserUriService
{
    List<RbacUri> selectMenuListByUserId(Integer id);

    List<RbacUri> selectUriListByUserId(Integer id);
}
