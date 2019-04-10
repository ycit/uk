package com.ycit.user.service;

import com.ycit.common.bean.user.criteria.UserLoginForm;
import com.ycit.common.bean.user.entity.User;
import com.ycit.common.util.JwtUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 登录业务层
 *
 * @author keda
 * @date 2019/3/12
 */
@Service
public class LoginService {

    @Resource
    private UserService userService;

    public void login(UserLoginForm form) {
        User user = userService.findByUsername(form.getUsername());
        if (form.getPassword().equals(user.getPassword())) {
            String token = JwtUtil.generateJWT(user.getUsername());
//            JedisUtil.getInstance()
        }
    }

}
