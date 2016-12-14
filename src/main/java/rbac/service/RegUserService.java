package rbac.service;

import rbac.entity.RbacRegUser;

import java.util.List;

public interface RegUserService
{
    List<RbacRegUser> selectAllRegUsers();

    RbacRegUser selectRegUserByID(Integer id);

    int insertRegUser(RbacRegUser rbacRegUser);

    int deleteRegUserByID(Integer id);
}
