package com.ycit.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 入口
 *
 * @author chenxiaolei
 * @date 2019/4/2
 */
@SpringBootApplication
@EnableEurekaServer
public class ServerApplication {

    public static void main(String[]args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}
