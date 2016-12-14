package rbac.controller.rbac;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import rbac.entity.RbacUri;
import rbac.service.UriService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/uriController")
public class UriController
{
    @Resource
    private UriService uriService;

    @RequestMapping("/selectAllUris")
    @RequiresPermissions("system:uri:view")
    public ModelAndView selectAllUris(Integer draw)
    {
        ModelAndView mav = new ModelAndView("JsonView");
        List<RbacUri> uriList = uriService.selectUriList();
        if(draw != null)
        {
            mav.addObject("draw", draw);
        }
        mav.addObject("data", uriList);
        mav.addObject("recordsTotal", uriList.size());
        mav.addObject("recordsFiltered", uriList.size());
        return mav;
    }

    @RequestMapping("/addUri")
    @RequiresPermissions("system:uri:add")
    public ModelAndView addUri( @RequestParam("parentId")Integer parentId, @RequestParam("uriName")String uriName, @RequestParam("uriPermission")String uriPermission,
                                       @RequestParam("uriLoc")String uriLoc, @RequestParam("iconCls")String iconCls, @RequestParam("showType")Byte showType)
    {
        ModelAndView mav = new ModelAndView("JsonView");
        RbacUri rbacUri = new RbacUri(parentId, uriName, uriPermission, uriLoc, iconCls, showType, "#", (byte)0, new Date() , new Date());
        int result = uriService.insertUri(rbacUri);
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

    @RequestMapping("/editUri")
    @RequiresPermissions("system:uri:update")
    public ModelAndView editUri(@RequestParam("id")Integer id , @RequestParam("parentId")Integer parentId, @RequestParam("uriName")String uriName,
                                @RequestParam("uriPermission")String uriPermission, @RequestParam("uriLoc")String uriLoc, @RequestParam("iconCls")String iconCls,
                                @RequestParam("showType")Byte showType)
    {
        ModelAndView mav = new ModelAndView("JsonView");
        RbacUri rbacUri = new RbacUri(id ,parentId, uriName, uriPermission, uriLoc, iconCls, showType, "#", (byte)0, null , new Date());
        int result = uriService.updateUri(rbacUri);
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

    @RequestMapping("/deleteUri")
    @RequiresPermissions("system:uri:delete")
    public ModelAndView deleteUri(@RequestParam("id")Integer id)
    {
        ModelAndView mav = new ModelAndView("JsonView");
        int result = uriService.deleteUriById(id);
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

}
