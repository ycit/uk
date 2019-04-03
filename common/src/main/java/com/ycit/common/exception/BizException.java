package com.ycit.common.exception;

import lombok.Data;

/**
 * 业务异常
 *
 * @author chenxiaolei
 * @date 2019/4/3
 */
@Data
public class BizException extends RuntimeException {

    private int code;

    private String msg;

    public BizException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BizException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
