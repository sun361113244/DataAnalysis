package sys.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/html")
public class HtmlController
{
    @RequestMapping("/homepage.action")
    public String homepage()
    {
        return "forward:/html/homepage.html";
    }

    @RequestMapping("/dataquery.action")
    public String dataquery()
    {
        return "forward:/analysis/html/dataquery.html";
    }

    @RequestMapping("/login.action")
    public String login()
    {
        return "forward:/html/login.html";
    }

    @RequestMapping("/unauthorized.action")
    public String unauthorized()
    {
        return "forward:/html/unauthorized.html";
    }

}
