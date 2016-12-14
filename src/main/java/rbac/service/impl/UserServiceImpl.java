package rbac.service.impl;

import org.springframework.stereotype.Service;
import rbac.entity.RbacUser;
import rbac.mapper.RbacUserMapper;
import rbac.service.UserService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    @Resource
    private RbacUserMapper rbacUserMapper;

    public RbacUser getRbacUserByUserCode(String userCode)
    {
        return rbacUserMapper.getRbacUserByUserCode(userCode);
    }

    public List<RbacUser> selectAllUsers()
    {
        return rbacUserMapper.selectAllUsers();
    }

    public int selectIsUserCodeExist(String name)
    {
        return rbacUserMapper.selectIsUserCodeExist(name);
    }

    public int selectIsUserCodeExistExceptId(Integer id, String name)
    {
        return rbacUserMapper.selectIsUserCodeExistExceptId( id , name);
    }

    public int isPasswordCorrect(Integer userid, String curPwd)
    {
        return rbacUserMapper.isPasswordCorrect(userid ,curPwd );
    }

    public int insertRabUser(RbacUser rbacUser)
    {
        return rbacUserMapper.insertRabUser(rbacUser);
    }

    public int deleteUserById(Integer id)
    {
        return rbacUserMapper.deleteUserById(id);
    }

    public int updateUser(RbacUser rbacUser)
    {
        return rbacUserMapper.updateUser(rbacUser);
    }

    public int updateUserStatusById(Integer id, Integer status)
    {
        return rbacUserMapper.updateUserStatusById( id , status);
    }

    public int updateCurrentUserPwd(Integer userid, String pwd, Date now)
    {
        return rbacUserMapper.updateCurrentUserPwd(userid , pwd , now);
    }

    public int updateCurrentUserInfo(Integer userid, String userName, String givenName, Date now)
    {
        return rbacUserMapper.updateCurrentUserInfo(userid ,userName , givenName , now);
    }

    public int updateLoginTime(String loginName, Date now)
    {
        rbacUserMapper.updateLoginTime(loginName, now);
        return 1;
    }

}
