package com.ycit.user.controller;

import com.ycit.common.bean.resp.ResponseMsg;
import com.ycit.common.bean.user.criteria.UserLoginForm;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * 登入控制层
 *
 * @author uk
 * 2019/3/10 16:25
 */
@RestController
public class LoginController {

    @PostMapping("/jwt/login")
    @ApiOperation(value = "JWT 登录验证", httpMethod = "POST")
    public ResponseMsg login(@Valid UserLoginForm form, BindingResult result) {
        if (result.hasErrors()) {
            String errors = result.getAllErrors()
                    .stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()).toString();
            return ResponseMsg.error(400, errors);
        }
        return ResponseMsg.ok();
    }

}
