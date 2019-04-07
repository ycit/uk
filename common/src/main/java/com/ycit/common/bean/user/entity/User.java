package com.ycit.common.bean.user.entity;

import com.ycit.common.bean.base.BaseEntity;
import lombok.Data;

/**
 * 用户实体
 *
 * @author uk
 * 2019/3/10 10:19
 */
@Data
public class User extends BaseEntity {

    private String username;
    private String password;
    private String telephone;
    private String email;


}
