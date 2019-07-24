package com.ycit.common.bean.resp;

import lombok.Data;

import java.util.List;

/**
 * 接口统一返回格式定义
 *
 * @author keda
 * @date 2019/3/7
 */
@Data
public class ResponseMsg<T> {

    private List<T> results;
    private int total;
    private int code;
    private String msg;
    private long timestamp;

    public static <T> ResponseMsg<T> ok() {
        ResponseMsg<T> responseMsg = new ResponseMsg<>();
        responseMsg.setCode(200);
        responseMsg.setTimestamp(System.currentTimeMillis());
        return responseMsg;
    }

    public static <T> ResponseMsg<T> ok(List<T> results, int total) {
        ResponseMsg<T> responseMsg = new ResponseMsg<>();
        responseMsg.setResults(results);
        responseMsg.setCode(200);
        responseMsg.setTotal(total);
        responseMsg.setTimestamp(System.currentTimeMillis());
        return responseMsg;
    }

    public static ResponseMsg error(int code, String msg) {
        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.setCode(code);
        responseMsg.setMsg(msg);
        responseMsg.setTimestamp(System.currentTimeMillis());
        return responseMsg;
    }



}
