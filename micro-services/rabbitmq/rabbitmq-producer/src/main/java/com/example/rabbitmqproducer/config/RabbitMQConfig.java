package com.example.rabbitmqproducer.config;

import com.example.rabbitmqproducer.enums.RabbitEnums;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-10-27 17:20
 * @Version V1.0
 */
@Configuration
@EnableRabbit
public class RabbitMQConfig {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Bean
    public Queue defaultQueue() {
        return new Queue(RabbitEnums.QueueEnum.DEFAULT_QUEUE.getName(), true);
    }


    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(RabbitEnums.ExchangeEnum.DEFAULT_EXCHANGE.getName(), true, false);
    }


    @Bean
    public Binding bindingDirectExchange(Queue directQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue).to(directExchange).with(RabbitEnums.RoutEnum.DEFAULT_ROUT.getName());
    }

}
