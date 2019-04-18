package com.ycit.user.service;

import com.ycit.common.bean.user.criteria.UserLoginForm;
import com.ycit.common.bean.user.entity.User;
import com.ycit.common.util.Constant;
import com.ycit.common.util.JedisUtil;
import com.ycit.common.util.JwtUtil;
import com.ycit.user.bean.dto.AuthToken;
import com.ycit.user.bean.enums.RedisKeyEnum;
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

    public AuthToken login(UserLoginForm form) {
        User user = userService.findByUsername(form.getUsername());
        if (form.getPassword().equals(user.getPassword())) {
            String token = JwtUtil.generateJWT(user.getUsername());
            JedisUtil.getInstance().setex(RedisKeyEnum.USER.getKey(), Constant.TOKEN_EXPIRATION_SECOND, token);
            return AuthToken.builder().token(token).build();
        }
        return null;
    }

}
