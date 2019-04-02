package com.ycit.log.config;

import com.ycit.log.aop.LogAspect;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @author chenxiaolei
 * @date 2019/4/2
 */
@Configuration
@ComponentScan(basePackages = {"com.ycit.log.aop"})
public class AppConfig {
}
