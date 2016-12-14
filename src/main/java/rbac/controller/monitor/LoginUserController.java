package rbac.controller.monitor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import rbac.entity.ActiveUser;
import rbac.entity.LoginUser;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/loginUserController")
public class LoginUserController
{
    @Resource
    private SessionDAO sessionDAO;

    @RequestMapping("/selectAllLoginUser")
    @RequiresPermissions("system:monitor_loginUser:view")
    public ModelAndView selectAllLogs(Integer draw)
    {
        ModelAndView mav = new ModelAndView("DataTablesAjaxView");
        Collection<Session> sessions =  sessionDAO.getActiveSessions();
        if(draw != null)
        {
            mav.addObject("draw", draw);
        }

        List<LoginUser> loginUsers = new ArrayList<LoginUser>();
        for(Session session:sessions)
        {
            SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if(simplePrincipalCollection != null)
            {
                ActiveUser activeUser = (ActiveUser)simplePrincipalCollection.getPrimaryPrincipal();

                LoginUser loginUser = new LoginUser();
                loginUser.setUsercode(activeUser.getUsercode());
                loginUser.setSrartTimestamp(session.getStartTimestamp());
                loginUser.setLastAccessTime(session.getLastAccessTime());
                loginUser.setHost(session.getHost());
                loginUser.setSessionId(session.getId().toString());

                if(session.getAttribute("SESSION_FORCE_LOGOUT") == null)
                {
                    loginUser.setExpired(false);
                }
                else
                {
                    loginUser.setExpired(true);
                }
                loginUsers.add(loginUser);
            }
        }
        mav.addObject("data", loginUsers);
        mav.addObject("recordsTotal", loginUsers.size());
        mav.addObject("recordsFiltered", loginUsers.size());
        return mav;
    }

    @RequestMapping("/forceLogout")
    //@RequiresPermissions("system:monitor_loginUser:forceLogout")
    public ModelAndView forceLogout(@RequestParam("sessionId") String sessionId)
    {
        ModelAndView mav = new ModelAndView("JsonView");

        Session session = sessionDAO.readSession(sessionId);
        if(session != null)
        {
            session.setAttribute("SESSION_FORCE_LOGOUT" , true);
            mav.addObject("result" , 1);
        }
        else
        {
            mav.addObject("result" , -1);
        }
        return mav;
    }

}
