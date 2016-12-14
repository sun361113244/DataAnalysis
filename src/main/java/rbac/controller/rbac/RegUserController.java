package rbac.controller.rbac;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import rbac.entity.RbacRegUser;
import rbac.entity.RbacUser;
import rbac.service.RegUserService;
import rbac.service.UserService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/regUserController")
public class RegUserController
{
    @Resource
    private UserService userService;

    @Resource
    private RegUserService regUserService;

    @RequestMapping("selectAllRegUsers")
    @RequiresPermissions("system:regUser:view")
    public ModelAndView selectAllRegUsers(Integer draw)
    {
        ModelAndView mav = new ModelAndView("DataTablesAjaxView");
        List<RbacRegUser> regUserList = regUserService.selectAllRegUsers();

        if(draw != null)
        {
            mav.addObject("draw", draw);
        }
        mav.addObject("data", regUserList);
        mav.addObject("recordsTotal", regUserList.size());
        mav.addObject("recordsFiltered", regUserList.size());
        return mav;
    }

    @RequestMapping("AddRegUser")
    @RequiresPermissions("system:regUser:add")
    public ModelAndView AddRegUser(Integer id)
    {
        ModelAndView mav = new ModelAndView("JsonView");

        RbacRegUser rbacRegUser = regUserService.selectRegUserByID(id);
        RbacUser rbacUser = new RbacUser();
        rbacUser.setUserName(rbacRegUser.getReg_name());
        rbacUser.setUserPasswrod(rbacRegUser.getReg_pwd());
        rbacUser.setGivenName(rbacRegUser.getReg_code());
        rbacUser.setStatus((byte) 1);
        rbacUser.setCreateTime(new Date());
        rbacUser.setUpdateTime(new Date());
        rbacUser.setLoginCount(0);
        regUserService.deleteRegUserByID(id);

        userService.insertRabUser(rbacUser);

        mav.addObject("result" ,1);
        return mav;
    }

    @RequestMapping("RemoveRegUser")
    @RequiresPermissions("system:regUser:remove")
    public ModelAndView RemoveRegUser(Integer id)
    {
        ModelAndView mav = new ModelAndView("JsonView");

        int res = regUserService.deleteRegUserByID(id);
        if(res > 0)
            mav.addObject("result" ,1);
        else
            mav.addObject("result" , 0);
        return mav;
    }
}
