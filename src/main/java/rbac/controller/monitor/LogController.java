package rbac.controller.monitor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import rbac.entity.RbacLog;
import rbac.service.LogService;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/logController")
public class LogController
{
    @Resource
    private LogService logService;

    @RequestMapping("/selectAllLogs")
    @RequiresPermissions("system:monitor:view")
    public ModelAndView selectAllLogs(Integer draw)
    {
        ModelAndView mav = new ModelAndView("DataTablesAjaxView");
        List<RbacLog> logList = logService.selectLogList();

        if(draw != null)
        {
            mav.addObject("draw", draw);
        }
        mav.addObject("data", logList);
        mav.addObject("recordsTotal", logList.size());
        mav.addObject("recordsFiltered", logList.size());
        return mav;
    }

}
