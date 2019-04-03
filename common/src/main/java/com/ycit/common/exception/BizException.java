package com.ycit.common.exception;

import lombok.Data;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BizException that = (BizException) o;
        return code == that.code &&
                Objects.equals(msg, that.msg);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), code, msg);
    }
}
