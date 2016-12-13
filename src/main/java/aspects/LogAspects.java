package aspects;

import com.alibaba.fastjson.JSON;
import entity.AnalysisLog;
import exception.ArgsException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import service.LogService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeoutException;

@Aspect
@Component
@Order(1)
public class LogAspects
{
    @Resource
    private LogService logService;

    @Around("execution(* api..*(..))")
    public ModelAndView logRound(ProceedingJoinPoint pjp)
    {
        ModelAndView mav = new ModelAndView("JsonView");
        String exceptionMsg = "";

        long startTime=System.currentTimeMillis();

        try
        {
            mav = (ModelAndView) pjp.proceed();
        } catch (TimeoutException ex)
        {
            exceptionMsg = ex.toString();
            mav.addObject("result" , -3);
            mav.addObject("msg" , "超时");
        } catch (ArgsException ex)
        {
            exceptionMsg = ex.getMessage();
            mav.addObject("result" , -4);
            mav.addObject("msg" , exceptionMsg);
        }
        catch (Throwable throwable)
        {
            exceptionMsg = throwable.getMessage();
            mav.addObject("result" , -2);
            mav.addObject("msg" , "数据库操作错误");
        }

        long endTime=System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        AnalysisLog analysisLog = new AnalysisLog();
        analysisLog.setOp_uri(pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
        analysisLog.setOp_ip(request.getRemoteAddr() + ":" + request.getRemotePort());
        analysisLog.setOp_userid(request.getRemoteUser());
        analysisLog.setOp_res(mav.getModel().get("result") != null ?mav.getModel().get("result").toString() : null);
        analysisLog.setOp_excu_time(new Date());
        analysisLog.setOp_params(JSON.toJSONString(pjp.getArgs()));
        analysisLog.setOp_exception(exceptionMsg);
        analysisLog.setOp_consume_time(endTime - startTime);

        logService.writeLog(analysisLog);

        return  mav;
    }
}
