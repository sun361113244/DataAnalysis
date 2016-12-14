package rbac.reamls;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import rbac.entity.ActiveUser;
import rbac.entity.RbacDep;
import rbac.entity.RbacUri;
import rbac.entity.RbacUser;
import rbac.service.UserDepService;
import rbac.service.UserService;
import rbac.service.UserUriService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class UserRealm extends AuthorizingRealm
{
    @Resource
    private UserService userService;

    @Resource
    private UserUriService userUriService;

    @Resource
    private UserDepService userDepService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        ActiveUser activeUser = (ActiveUser)principals.getPrimaryPrincipal();

        List<RbacUri> permisstionList = null;
        List<String> permissions = new ArrayList<String>();
        try
        {
            permisstionList = userUriService.selectUriListByUserId(activeUser.getUserid());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        for(int i=0;i<permisstionList.size();i++)
        {

            permissions.add(permisstionList.get(i).getPermission());
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        // token是用户输入的用户名和密码
        String userCode = (String)token.getPrincipal();

        RbacUser rbacUser = null;
        try
        {
            rbacUser = userService.getRbacUserByUserCode(userCode);
        }
        catch ( Exception ex)
        {
            ex.printStackTrace();
        }
        if(rbacUser == null)
        {
            throw new UnknownAccountException();//没找到帐号
        }
        if (rbacUser.getStatus() == 2)
        {
            throw new LockedAccountException(); //帐号锁定
        }

        List<RbacUri> menus = null;
        List<RbacUri> permisstions = null;
        try
        {
            menus = userUriService.selectMenuListByUserId(rbacUser.getId());
            permisstions = userUriService.selectUriListByUserId(rbacUser.getId());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        List<RbacDep> departments = null;

        try
        {
            departments = userDepService.selectDepListByUserId(rbacUser.getId());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUserid(rbacUser.getId());
        activeUser.setUsercode(rbacUser.getUserName());
        activeUser.setUsername(rbacUser.getGivenName());
        activeUser.setMenus(menus);
        activeUser.setPermissions(permisstions);
        activeUser.setDepartments(departments);

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activeUser, rbacUser.getUserPasswrod(),this.getName());

        return simpleAuthenticationInfo;
    }

    public void clearCached()
    {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
