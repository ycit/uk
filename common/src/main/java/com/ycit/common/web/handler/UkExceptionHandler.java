package com.ycit.common.web.handler;

import com.ycit.common.bean.resp.ResponseMsg;
import com.ycit.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常拦截处理
 *
 * @author chenxiaolei
 * @date 2019/4/3
 */
@Slf4j
@RestControllerAdvice
public class UkExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseMsg serverError(Throwable throwable) {
        log.error("not handler exception {}", throwable);
        return ResponseMsg.error(500, "服务器内部错误");
    }

    @ExceptionHandler(BizException.class)
    public ResponseMsg bizException(BizException biz) {
        log.error("biz exception is {}", biz);
        return ResponseMsg.error(biz.getCode(), biz.getMsg());
    }



}
