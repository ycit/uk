package com.ycit.common.web.aop;

import com.ycit.common.web.service.ConstantService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * log 切面
 *
 * @author chenxiaolei
 * @date 2019/4/2
 */
@Aspect
@Component
@Slf4j
@AutoConfigureAfter(ConstantService.class)
public class LogAspect {

    @Resource
    private ConstantService constantService;

    @Resource
    private KafkaTemplate<Integer, String> kafkaTemplate;

    @Pointcut(value = "@annotation(com.ycit.common.aop.OperationLog)")
    public void pointcut() {
    }

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint joinPoint)throws Throwable {
        String controller = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        log.debug("the method {}.{} run...", controller, method);
//        kafkaTemplate.send(constantService.getLogTopic());
        return joinPoint.proceed();
    }

}
