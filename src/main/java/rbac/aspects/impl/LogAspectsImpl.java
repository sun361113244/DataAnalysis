package rbac.aspects.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import rbac.aspects.LogAspects;
import rbac.entity.ActiveUser;
import rbac.entity.RbacLog;
import rbac.service.LogService;

import javax.annotation.Resource;
import java.util.Date;

public class LogAspectsImpl implements LogAspects
{
    @Resource
    private LogService logService;

    public Object logRound(ProceedingJoinPoint pjp) throws Throwable
    {
        Object obj = null;
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.isAuthenticated())
        {
            long startTime=System.currentTimeMillis();
            obj = pjp.proceed();
            long endTime=System.currentTimeMillis();

            ActiveUser activeUser = (ActiveUser) currentUser.getPrincipal();
            Session session = currentUser.getSession();

            RbacLog rbacLog = new RbacLog();
            rbacLog.setOp_userid(activeUser.getUserid());
            rbacLog.setOp_ip(session.getHost());
            rbacLog.setOp_uri(pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
            rbacLog.setOp_excu_time(new Date());
            rbacLog.setOp_params("");
            rbacLog.setOp_res("");
            rbacLog.setOp_exception("");
            rbacLog.setOp_consume_time(endTime - startTime);

            logService.insertLog(rbacLog);
        }
        else
        {
            obj = pjp.proceed();
        }

        return  obj;
    }
}
