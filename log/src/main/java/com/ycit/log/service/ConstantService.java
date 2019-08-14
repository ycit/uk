package com.ycit.log.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 常量汇集地
 *
 * @author chenxiaolei
 * @date 2019/4/3
 */
@Data
@Service
public class ConstantService {

//    @Value("${kafka.bootstraps}")
//    private String kafkaBootStraps;

    @Value("${topic.log}")
    private String logTopic;
}
