package rbac.aspects;

import org.aspectj.lang.ProceedingJoinPoint;

public interface LogAspects
{
    Object logRound(ProceedingJoinPoint pjp)throws Throwable;
}
