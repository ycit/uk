package com.ycit.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 入口
 *
 * @author uk
 * 2019/3/10 16:50
 */
@EnableSwagger2
@EnableAspectJAutoProxy
@SpringBootApplication
@MapperScan(basePackages = {"com.ycit.user.mapper"})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
