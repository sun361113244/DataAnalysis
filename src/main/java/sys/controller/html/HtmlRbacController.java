package sys.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rbac/html")
public class HtmlRbacController
{
    @RequestMapping("/queryDepartmentManager.action")
    public String queryDepartmentManager()
    {
        return "forward:/rbac/html/queryDepartmentManager.html";
    }
    @RequestMapping("/queryLoginUser.action")
    public String queryLoginUser()
    {
        return "forward:/rbac/html/queryLoginUser.html";
    }
    @RequestMapping("/queryLogs.action")
    public String queryLogs()
    {
        return "forward:/rbac/html/queryLogs.html";
    }
    @RequestMapping("/queryRegUsers.action")
    public String queryRegUsers()
    {
        return "forward:/rbac/html/queryRegUsers.html";
    }
    @RequestMapping("/queryRoleManager.action")
    public String queryRoleManager()
    {
        return "forward:/rbac/html/queryRoleManager.html";
    }
    @RequestMapping("/queryURLManager.action")
    public String queryURLManager()
    {
        return "forward:/rbac/html/queryURLManager.html";
    }
    @RequestMapping("/queryUserManager.action")
    public String queryUserManager()
    {
        return "forward:/rbac/html/queryUserManager.html";
    }
}
