package com.ycit.common.bean.user.criteria;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户登录表单
 * @author uk
 *  2019/3/10 10:16
 */
@Data
public class UserLoginForm {

    private String username;

    @NotBlank
    private String loginName;
    
    @NotBlank
    private String password;

}
