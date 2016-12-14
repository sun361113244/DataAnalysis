package rbac.controller.rbac;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import rbac.entity.RbacRole;
import rbac.entity.RbacUri;
import rbac.reamls.UserRealm;
import rbac.service.RoleService;
import rbac.service.RoleUriService;
import rbac.service.UriService;
import sys.entity.zTreeNode;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/roleController")
public class RoleController
{
    @Resource
    private UserRealm userRealm;

    @Resource
    private RoleService roleService;

    @Resource
    private UriService uriService;

    @Resource
    private RoleUriService roleUriService;

    @RequestMapping("/selectAllRoles")
    @RequiresPermissions("system:role:view")
    public ModelAndView selectAllRoles(Integer draw)
    {
        ModelAndView mav = new ModelAndView("DataTablesAjaxView");
        List<RbacRole> roleList = roleService.selectAllRoles();
        if(draw != null)
        {
            mav.addObject("draw", draw);
        }
        mav.addObject("data", roleList);
        mav.addObject("recordsTotal", roleList.size());
        mav.addObject("recordsFiltered", roleList.size());
        return mav;
    }

    @RequestMapping("/selectTreeUrisByRoleId")
    @RequiresPermissions("system:role:authorizeroleuris")
    public ModelAndView selectTreeUrisByRoleId(@RequestParam("id")Integer id)
    {
        ModelAndView mav = new ModelAndView("zTreeView");
        List<RbacUri> uriList = uriService.selectUriList();
        List<RbacUri> idUriList = roleUriService.selectUriListByRoleId(id);

        List<zTreeNode> zTreeAllNodes  = new ArrayList<zTreeNode>();
        List<zTreeNode> zTreeCheckedNodes  = new ArrayList<zTreeNode>();
        for(RbacUri rbacUri : uriList)
        {
            zTreeNode node = new zTreeNode(rbacUri.getId() ,rbacUri.getParentId() , rbacUri.getName() ,true ,false );
            zTreeAllNodes.add(node);
        }
        for(RbacUri rbacUri : idUriList)
        {
            zTreeNode node = new zTreeNode(rbacUri.getId() ,rbacUri.getParentId() , rbacUri.getName() ,true ,true );
            zTreeCheckedNodes.add(node);
        }
        mav.addObject("zTreeAllNodes" , zTreeAllNodes);
        mav.addObject("zTreeCheckedNodes" , zTreeCheckedNodes);
        mav.addObject("result" , 1);
        return mav;
    }

    @RequestMapping("/addRole")
    @RequiresPermissions("system:role:add")
    public ModelAndView addRole( @RequestParam("name")String name, @RequestParam("description")String description)
    {
        ModelAndView mav = new ModelAndView("JsonView");
        RbacRole role = new RbacRole(null , name , description , new Date() , new Date(), 1);

        //角色名称存在 返回-2
        if(roleService.selectIsRoleNameExist(name) > 0)
        {
            mav.addObject("result" , -2);
            return mav;
        }
        int result = roleService.insertRole(role);
        userRealm.clearCached();
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

    @RequestMapping("/editRole")
    @RequiresPermissions("system:role:edit")
    public ModelAndView editRole(@RequestParam("id")Integer id , @RequestParam("name")String name,
                                @RequestParam("description")String description)
    {
        ModelAndView mav = new ModelAndView("JsonView");
        RbacRole role = new RbacRole(id , name , description , null , new Date(), 1);

        //角色名称以存在 返回-2
        if(roleService.selectIsRoleNameExistExceptID(id, name) > 0)
        {
            mav.addObject("result" , -2);
            return mav;
        }

        int result = roleService.updateRoleById(role);
        userRealm.clearCached();
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

    @RequestMapping("/deleteRole")
    @RequiresPermissions("system:role:delete")
    public ModelAndView deleteRole(@RequestParam("id")Integer id)
    {
        ModelAndView mav = new ModelAndView("JsonView");
        int result = roleService.deleteUserRoleById(id);
        userRealm.clearCached();

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

    @RequestMapping("/authorizeRoleUris")
    @RequiresPermissions("system:role:authorizeroleuris")
    public ModelAndView authorizeRoleUris(@RequestParam("id")Integer id ,@RequestParam("nodes[]")Integer[] nodes)
    {
        ModelAndView mav = new ModelAndView("JsonView");
        roleUriService.insertRoleUris(id , nodes);
        userRealm.clearCached();
        mav.addObject("result" , 1);
        return mav;
    }
}
