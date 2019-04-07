package com.ycit.user.service;

import com.ycit.common.bean.user.entity.User;
import com.ycit.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户业务层
 *
 * @author uk
 * 2019/4/7 15:23
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

}
