package com.ycit.common.bean.log.entity;

import lombok.Builder;
import lombok.Data;

/**
 * 操作日志
 *
 * @author uk
 * 2019/4/3 21:42
 */
@Data
@Builder
public class OperationLog {

    private Long id;
    private String logNo;
    private String offset;
    private String moduleName;
    private String description;
    private String reqUser;
    private String reqMethod;
    private String reqParams;
    private Integer isSuccess;
    private String errorReason;


}
