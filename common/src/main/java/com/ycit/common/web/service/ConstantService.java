package com.ycit.common.web.service;

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

    @Value("${topic.log}")
    private String logTopic;

}
