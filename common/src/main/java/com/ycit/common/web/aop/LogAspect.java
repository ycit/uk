package com.ycit.common.web.aop;

import com.ycit.common.annotation.DoLog;
import com.ycit.common.bean.log.entity.OperationLog;
import com.ycit.common.bean.resp.ResponseMsg;
import com.ycit.common.exception.BizException;
import com.ycit.common.util.JsonUtil;
import com.ycit.common.web.service.ConstantService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
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
    private KafkaTemplate<String, String> kafkaTemplate;

    @Pointcut(value = "@annotation(com.ycit.common.annotation.DoLog)")
    public void pointcut() {
    }

    @Around(value = "pointcut() && @annotation(doLog)")
    public Object around(ProceedingJoinPoint joinPoint, DoLog doLog) {
        String controller = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        log.debug("the method {}.{} run...", controller, method);
        Object[] args = joinPoint.getArgs();
        String kind = joinPoint.getKind();

        String arg = args.toString();
        Object object = null;
        try {
            object = joinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("log aspect catch exception, is {}", throwable);
            throw new BizException(500, "log aspect catch exception");
        }
        if (object instanceof ResponseMsg) {
            int code = ((ResponseMsg)object).getCode();
            int isSuccess = 0;
            String errorReason = null;
            if (200 == code) {
                isSuccess = 1;
            } else {
                errorReason = ((ResponseMsg)object).getMsg();
            }
            OperationLog operationLog = OperationLog.builder()
                    .logNo("111").moduleName(doLog.moduleName())
                    .description(doLog.description()).isSuccess(isSuccess)
                    .reqMethod(doLog.method()).reqParams("").build();
            ProducerRecord<String, String> record = new ProducerRecord<>("log", 0, "log", JsonUtil.objects2Json(operationLog));
//            ProducerRecord<String, String> record = new ProducerRecord<>("log", 1, "log", JsonUtil.objects2Json(operationLog));
            kafkaTemplate.send(record);
        }
        return object;
    }

}
