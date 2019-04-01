package com.ycit.user.controller;

import com.ycit.common.bean.resp.ResponseMsg;
import com.ycit.common.bean.user.criteria.UserLoginForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登入控制层
 *
 * @author uk
 * 2019/3/10 16:25
 */
@RestController
@Api(value = "用户登录登出", tags = {"用户登录登出"})
public class LoginController {

    @PostMapping("/jwt/oauth")
    @ApiOperation(value = "JWT 登录验证", httpMethod = "POST")
    public ResponseMsg login(UserLoginForm form) {
        return ResponseMsg.ok();
    }

}
