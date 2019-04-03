package com.ycit.log.config;

import com.ycit.log.service.ConstantService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * kafka 配置
 *
 * @author chenxiaolei
 * @date 2019/4/3
 */
//@Configuration
//@EnableKafka
//@AutoConfigureAfter(ConstantService.class)
public class KafkaConfig {

    @Resource
    private ConstantService constantService;

    @Bean
    ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<Integer, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> consumerConfigs = new HashMap<>(5);
        consumerConfigs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, constantService.getKafkaBootStraps());
        return consumerConfigs;
    }

}
