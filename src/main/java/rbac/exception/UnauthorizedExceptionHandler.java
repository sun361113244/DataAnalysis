package rbac.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by charles on 2015/10/12.
 */
public class UnauthorizedExceptionHandler implements HandlerExceptionResolver
{
    private static final Logger logger = LoggerFactory.getLogger(UnauthorizedExceptionHandler.class);

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    {
        if(ex instanceof UnauthorizedException)
        {
            ModelAndView mav = new ModelAndView("JsonView");
            mav.addObject("result" , 403);
            return mav;
        }
        else
        {
            if(ex instanceof DataIntegrityViolationException)
            {
                ModelAndView mav = new ModelAndView("JsonView");
                mav.addObject("result" , -1);
                return mav;
            }
            else
            {
                if(ex instanceof SQLException)
                {
                    ModelAndView mav = new ModelAndView("JsonView");
                    mav.addObject("result" , -1);
                    return mav;
                }
                else
                {
                    return null;
                }
            }
        }

    }
}
