package com.ycit.log.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

/**
 * @author uk
 * 2019/4/20 11:50
 */
@Slf4j
public class LogTopicListener {

    @KafkaListener(topics = "log")
    public void listen(ConsumerRecord record, Acknowledgment acknowledgment) {
        log.debug("consumer start, topic is {}, partition is {}, offset is {} ...",
                record.topic(), record.partition(), record.offset());
    }

}
