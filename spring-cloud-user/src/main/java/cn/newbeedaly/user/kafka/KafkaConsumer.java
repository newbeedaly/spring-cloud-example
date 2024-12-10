package cn.newbeedaly.user.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * 日志消息消费者
 */
@Slf4j
@Component
public class KafkaConsumer {

    @Bean
    public Consumer<String> kafkaMsg() {
        return message -> {
            log.info("kafkaMsg 消息消费成功:{}", message);
        };
    }

}

