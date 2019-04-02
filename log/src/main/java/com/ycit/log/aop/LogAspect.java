package com.ycit.log.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * log 切面
 *
 * @author chenxiaolei
 * @date 2019/4/2
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

//    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut(value = "@annotation(com.ycit.log.aop.OperationLog)")
//    @Pointcut(value = "execution(* com.ycit.user..*(..))")
    public void pointcut() {

    }

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        String controller = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        log.debug("the method {}.{} run...", controller, method);
        Object obj = null;
        try {
            obj =  joinPoint.proceed();
        } catch (Throwable throwable) {

        }
        return obj;
    }

}
