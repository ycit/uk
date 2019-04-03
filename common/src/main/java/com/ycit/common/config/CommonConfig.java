package com.ycit.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * 配置类
 *
 * @author chenxiaolei
 * @date 2019/4/3
 */
@Configuration
@EnableKafka
@ComponentScan(basePackages = {"com.ycit.common.web"})
public class CommonConfig {
}
