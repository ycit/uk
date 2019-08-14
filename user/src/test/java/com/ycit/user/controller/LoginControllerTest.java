package com.ycit.user.controller;

import com.ycit.common.bean.resp.ResponseMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 登录测试类
 *
 * @author uk
 * 2019/7/24 21:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerTest {

    @LocalServerPort
    private int port;

    @Resource
    private TestRestTemplate restTemplate;

    @Test
    public void loginTest(){
        restTemplate.postForEntity("http://localhost:" + port +"/jwt/login", null, ResponseMsg.class);
    }


}
