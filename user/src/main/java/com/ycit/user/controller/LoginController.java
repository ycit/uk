package com.ycit.user.controller;

import com.google.common.collect.ImmutableList;
import com.ycit.common.bean.resp.ResponseMsg;
import com.ycit.common.bean.user.criteria.UserLoginForm;
import com.ycit.log.annotation.DoLog;
import com.ycit.user.bean.dto.AuthToken;
import com.ycit.user.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * 登入控制层
 *
 * @author uk
 * 2019/3/10 16:25
 */
@RestController
@Api(value = "用户登录登出", tags = {"用户登录登出"})
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/jwt/login")
    @ApiOperation(value = "JWT 登录验证", httpMethod = "POST")
    @DoLog(moduleName = "用户", description = "用户登录", method = "post")
    public ResponseMsg login(@Valid UserLoginForm form, BindingResult result) {
        if (result.hasErrors()) {
            String errors = result.getAllErrors()
                    .stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()).toString();
            return ResponseMsg.error(400, errors);
        }
        AuthToken authToken = loginService.login(form);
        if (authToken != null) {
            return ResponseMsg.ok(ImmutableList.of(authToken), 1);
        }
        return ResponseMsg.error(401, "登录失败");
    }

}
