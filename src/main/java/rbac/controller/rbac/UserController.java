package rbac.controller.rbac;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import rbac.entity.*;
import rbac.service.*;
import sys.entity.zTreeNode;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/userController")
public class UserController
{
    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private DepService depService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private UserDepService userDepService;

    @RequestMapping("/selectAllUsers")
    @RequiresPermissions("system:user:view")
    public ModelAndView selectAllUsers(Integer draw)
    {
        ModelAndView mav = new ModelAndView("DataTablesAjaxView");
        List<RbacUser> userList = userService.selectAllUsers();
        if(draw != null)
        {
            mav.addObject("draw", draw);
        }
        mav.addObject("data", userList);
        mav.addObject("recordsTotal", userList.size());
        mav.addObject("recordsFiltered", userList.size());
        return mav;
    }

    @RequestMapping("/selectTreeRolesByUserId")
    @RequiresPermissions("system:user:authorizerole")
    public ModelAndView selectTreeRolesByUserId(@RequestParam("id")Integer id)
    {
        // 获取所有权限
        ModelAndView mav = new ModelAndView("zTreeView");
        List<RbacRole> roleList = roleService.selectAllRoles();
        List<RbacRole> idRoleList = userRoleService.selectRolesById(id);

        List<zTreeNode> zTreeAllNodes  = new ArrayList<zTreeNode>();
        List<zTreeNode> zTreeCheckedNodes  = new ArrayList<zTreeNode>();
        for(RbacRole role : roleList)
        {
            zTreeNode node = new zTreeNode(role.getId() ,0 , role.getDescription() ,true ,false );
            zTreeAllNodes.add(node);
        }
        for(RbacRole role : idRoleList)
        {
            zTreeNode node = new zTreeNode(role.getId() ,0 , role.getDescription() ,true ,true );
            zTreeCheckedNodes.add(node);
        }
        mav.addObject("zTreeAllNodes" , zTreeAllNodes);
        mav.addObject("zTreeCheckedNodes" , zTreeCheckedNodes);
        mav.addObject("result" , 1);
        return mav;
    }

    @RequestMapping("/selectTreeDepsByUserId")
    @RequiresPermissions("system:user:authorizedep")
    public ModelAndView selectTreeDepsByUserId(@RequestParam("id")Integer id)
    {
        // 获取所有权限
        ModelAndView mav = new ModelAndView("zTreeView");

        List<RbacDep> depList = depService.selectDepList();
        List<RbacDep> idDepList = userDepService.selectDepListByUserId(id);

        List<zTreeNode> zTreeAllNodes  = new ArrayList<zTreeNode>();
        List<zTreeNode> zTreeCheckedNodes  = new ArrayList<zTreeNode>();
        for(RbacDep dep : depList)
        {
            zTreeNode node = new zTreeNode(dep.getId() ,dep.getParentId() , dep.getName() ,true ,false );
            zTreeAllNodes.add(node);
        }
        for(RbacDep dep : idDepList)
        {
            zTreeNode node = new zTreeNode(dep.getId() ,dep.getParentId() , dep.getName() ,true ,true );
            zTreeCheckedNodes.add(node);
        }
        mav.addObject("zTreeAllNodes" , zTreeAllNodes);
        mav.addObject("zTreeCheckedNodes" , zTreeCheckedNodes);
        mav.addObject("result" , 1);
        return mav;
    }

    @RequestMapping("/addUser")
    @RequiresPermissions("system:user:add")
    public ModelAndView addUser(@RequestParam("name")String name ,@RequestParam("pwd")String pwd ,
                                @RequestParam("givenname")String givenname , @RequestParam("status")Byte status)
    {
        ModelAndView mav = new ModelAndView("JsonView");
        Md5Hash md5Hashpwd = new Md5Hash(pwd );
        RbacUser rbacUser = new RbacUser(null , name, md5Hashpwd.toString(), givenname ,status ,new Date(), new Date() , null , 0 );
        // 用户编号存在 返回-2
        if(userService.selectIsUserCodeExist(name) > 0)
        {
            mav.addObject("result" , -2);
            return mav;
        }
        int result = userService.insertRabUser(rbacUser);
        if(result == 1)
        {
            mav.addObject("result" ,1);
        }
        else
        {
            mav.addObject("result" ,result);
        }
        return mav;
    }

    @RequestMapping("/editUser")
    @RequiresPermissions("system:user:edit")
    public ModelAndView editUser(@RequestParam("id")Integer id , @RequestParam("name")String name , @RequestParam("pwd")String pwd ,
                                 @RequestParam("givenname")String givenname , @RequestParam("status")Byte status)
    {
        ModelAndView mav = new ModelAndView("JsonView");
        Md5Hash md5Hashpwd = new Md5Hash(pwd );
        RbacUser rbacUser = new RbacUser(id , name, md5Hashpwd.toString(), givenname ,status , null, new Date() , null , 0 );
        // 用户编号存在 返回-2
        if(userService.selectIsUserCodeExistExceptId(id, name) > 0)
        {
            mav.addObject("result" , -2);
            return mav;
        }
        int result = userService.updateUser(rbacUser);
        if(result == 1)
        {
            mav.addObject("result" ,1);
        }
        else
        {
            mav.addObject("result" ,result);
        }
        return mav;
    }

    @RequestMapping("/deleteUser")
    @RequiresPermissions("system:user:delete")
    public ModelAndView deleteUser(@RequestParam("id")Integer id)
    {
        ModelAndView mav = new ModelAndView("JsonView");
        int result = userService.updateUserStatusById(id, 2);
        if(result == 1)
        {
            mav.addObject("result" ,1);
        }
        else
        {
            mav.addObject("result" ,result);
        }
        return mav;
    }

    @RequestMapping("/authorizeUserRoles")
    @RequiresPermissions("system:user:authorizerole")
    public ModelAndView authorizeUserRoles(@RequestParam("id")Integer id ,@RequestParam("nodes[]")Integer[] nodes)
    {
        ModelAndView mav = new ModelAndView("JsonView");
        userRoleService.insertUserRoles(id , nodes);
        mav.addObject("result" , 1);
        return mav;
    }

    @RequestMapping("/authorizeUserDeps")
    @RequiresPermissions("system:user:authorizedep")
    public ModelAndView authorizeUserDeps(@RequestParam("id")Integer id ,@RequestParam("nodes[]")Integer[] nodes)
    {
        ModelAndView mav = new ModelAndView("JsonView");
        userDepService.insertUserDeps(id, nodes);
        mav.addObject("result" , 1);
        return mav;
    }

    @RequestMapping("/UpdateCurrentUserPassword")
    @RequiresPermissions("system:user:updatePassword")
    public ModelAndView UpdateCurrentUserPassword(@RequestParam("curPwd")String curPwd, @RequestParam("setPwd")String setPwd)
    {
        Subject currentUser = SecurityUtils.getSubject();
        ActiveUser activeUser = (ActiveUser) currentUser.getPrincipal();
        Md5Hash md5HashCurPwd = new Md5Hash(curPwd );
        Md5Hash md5HashSetPwd = new Md5Hash(setPwd );

        ModelAndView mav = new ModelAndView("JsonView");
        int res = userService.isPasswordCorrect(activeUser.getUserid(), md5HashCurPwd.toString());
        if(res ==  0)
        {
            mav.addObject("result" , -2);
            return mav;
        }
        res = userService.updateCurrentUserPwd(activeUser.getUserid() , md5HashSetPwd.toString() , new Date());
        mav.addObject("result" , res);
        return mav;
    }

    @RequestMapping(value =  "/UpdateCurrentUserInfo" )
    @RequiresPermissions("system:user:updateInfo")
    public ModelAndView UpdateCurrentUserInfo(@RequestParam("userName")String userName, @RequestParam("givenName")String givenName)
    {
        String user_Name = null;
        String given_Name = null;
        try
        {
            user_Name = new String(userName.getBytes("iso-8859-1"),"utf-8");
            given_Name = new String(givenName.getBytes("iso-8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        Subject currentUser = SecurityUtils.getSubject();
        ActiveUser activeUser = (ActiveUser) currentUser.getPrincipal();
        ModelAndView mav = new ModelAndView("JsonView");

        if(userService.selectIsUserCodeExistExceptId(activeUser.getUserid(), user_Name) > 0)
        {
            mav.addObject("result" , -2);
            return mav;
        }

        int res = userService.updateCurrentUserInfo(activeUser.getUserid(), user_Name, given_Name ,new Date());
        mav.addObject("result" , res);
        return mav;
    }
}
