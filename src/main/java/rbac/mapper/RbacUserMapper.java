package rbac.mapper;

import org.apache.ibatis.annotations.Param;
import rbac.entity.RbacUser;

import java.util.Date;
import java.util.List;

public interface RbacUserMapper
{
    RbacUser getRbacUserByUserCode(String userCode);

    List<RbacUser> selectAllUsers();

    int selectIsUserCodeExist(String name);

    int selectIsUserCodeExistExceptId(@Param("id") Integer id, @Param("name") String name);

    int isPasswordCorrect(@Param("userid") Integer userid, @Param("curPwd") String curPwd);

    int insertRabUser(RbacUser rbacUser);

    int deleteUserById(Integer id);

    int updateUser(RbacUser rbacUser);

    int updateUserStatusById(@Param("id") Integer id, @Param("status") Integer status);

    int updateCurrentUserPwd(@Param("userid") Integer userid, @Param("pwd") String pwd, @Param("now") Date now);

    int updateCurrentUserInfo(@Param("userid") Integer userid, @Param("userName") String userName, @Param("givenName") String givenName, @Param("now") Date now);

    int updateLoginTime(@Param("loginName") String loginName, @Param("now") Date now);
}
