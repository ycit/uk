package com.ycit.user.bean.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 登录
 *
 * @author uk
 * 2019/4/13 14:42
 */
@Data
@Builder
public class AuthToken {

    private String token;

}
