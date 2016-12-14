package rbac.controller.rbac;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import rbac.entity.RbacDep;
import rbac.service.DepService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/departmentController")
public class DepartmentController
{
    @Resource
    private DepService depService;
    /**
     * 根据当前用户从数据库加载部门
     */
    @RequestMapping("/selectAllStation")
    @RequiresPermissions("system:department:view")
    public ModelAndView selectAllDepartment(Integer draw)
    {
        ModelAndView mav = new ModelAndView("DataTablesAjaxView");
        List<RbacDep> departmentList = depService.selectDepList();

        if(draw != null)
        {
            mav.addObject("draw", draw);
        }
        mav.addObject("data", departmentList);
        mav.addObject("recordsTotal", departmentList.size());
        mav.addObject("recordsFiltered", departmentList.size());
        return mav;
    }

    @RequestMapping("/addDepartment")
    @RequiresPermissions("system:department:add")
    public ModelAndView addDepartment( @RequestParam("code")String code, @RequestParam("name")String name ,
                                       @RequestParam("parentId")Integer parentId)
    {
        ModelAndView mav = new ModelAndView("JsonView");
        RbacDep dep = new RbacDep(code , parentId , name , new Date() , new Date());

        //站点编号存在 返回-2
        if(depService.selectIsDepCodeExist(code) > 0)
        {
            mav.addObject("result" , -2);
            return mav;
        }
        //站点名称存在 返回-3
        if(depService.selectIsDepNameExist(name) > 0)
        {
            mav.addObject("result" , -3);
            return mav;
        }
        int res = depService.insertDep(dep);
        mav.addObject("result" ,1);
        return mav;
    }

    @RequestMapping("/editDepartment")
    @RequiresPermissions("system:department:update")
    public ModelAndView editDep(@RequestParam("id")Integer id , @RequestParam("parentId")Integer parentId ,
                                @RequestParam("code")String code, @RequestParam("name")String name)
    {
        ModelAndView mav = new ModelAndView("JsonView");
        RbacDep dep = new RbacDep(id , parentId ,code , name , null , new Date());

        //站点编号存在 返回-2
        if(depService.selectIsDepCodeExistExceptID(id, code) > 0)
        {
            mav.addObject("result" , -2);
            return mav;
        }
        //站点名称存在 返回-3
        if(depService.selectIsDepNameExistExceptID(id, name) > 0)
        {
            mav.addObject("result" , -3);
            return mav;
        }

        int res = depService.updateDep(dep);
        mav.addObject("result" ,1);
        return mav;
    }

    @RequestMapping("/deleteDepartment")
    @RequiresPermissions("system:department:delete")
    public ModelAndView deleteDep(@RequestParam("id")Integer id)
    {
        ModelAndView mav = new ModelAndView("JsonView");
        RbacDep dep = new RbacDep( id ,null ,null , null, null , (byte)2);
        int res = depService.updateDep(dep);

        mav.addObject("result" ,1);
        return mav;
    }
}
