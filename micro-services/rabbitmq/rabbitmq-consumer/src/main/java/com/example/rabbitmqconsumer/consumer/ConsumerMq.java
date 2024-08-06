package com.example.rabbitmqconsumer.consumer;

import com.example.enums.RabbitEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author wangshuo
 * @Date 2024-08-06 15:22
 * @Version V1.0
 */
@Component
public class ConsumerMq {

    private final static Logger logger = LoggerFactory.getLogger(ConsumerMq.class);

    @RabbitListener(queues = RabbitEnums.DEFAULT_QUEUE)
    public void defaultQueue(String msg) {
        logger.error("接收到defaultQueue的消息：{}", msg);
    }


}
