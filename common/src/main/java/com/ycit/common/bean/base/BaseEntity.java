package com.ycit.common.bean.base;

import lombok.Data;

import java.sql.Date;

/**
 * entity 基类
 *
 * @author uk
 * 2019/4/3 21:45
 */
@Data
public class BaseEntity {

    private Long id;
    private String no;
    private Date createTime;
    private Date endTime;
    private Integer version;

}
