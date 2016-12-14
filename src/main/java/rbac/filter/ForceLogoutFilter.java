package rbac.filter;

import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForceLogoutFilter extends AccessControlFilter
{

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception
    {
        Session session = getSubject(request  , response).getSession(false);
        if(session == null)
        {
            return true;
        }
         return session.getAttribute("SESSION_FORCE_LOGOUT") == null;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        getSubject(request, response).logout();//强制退出

        String loginUrl = getLoginUrl();

        String requestType = httpRequest.getHeader("X-Requested-With");
        if(requestType != null && "XMLHttpRequest".equals(requestType))
        {
            httpServletResponse.setHeader("sessionstatus", "forceLogout");
            httpServletResponse.sendError(518, "session forceLogout.");
            httpServletResponse.setCharacterEncoding("UTF-8");
        }
        else
        {
            WebUtils.issueRedirect(request, response, loginUrl + "?loginStatus=KickOut");
        }
        return false;
    }
}
