package rbac.service;

import rbac.entity.RbacUser;

import java.util.Date;
import java.util.List;

public interface UserService
{
    RbacUser getRbacUserByUserCode(String userCode);

    List<RbacUser> selectAllUsers();

    int selectIsUserCodeExist(String name);

    int selectIsUserCodeExistExceptId(Integer id, String name);

    int isPasswordCorrect(Integer userid, String curPwd);

    int insertRabUser(RbacUser rbacUser);

    int deleteUserById(Integer id);

    int updateUser(RbacUser rbacUser);

    int updateUserStatusById(Integer id, Integer status);

    int updateCurrentUserPwd(Integer userid, String pwd, Date now);

    int updateCurrentUserInfo(Integer userid, String userName, String givenName, Date now);

    int updateLoginTime(String loginName, Date date);
}
