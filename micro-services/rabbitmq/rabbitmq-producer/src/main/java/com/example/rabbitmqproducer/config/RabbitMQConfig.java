package com.example.rabbitmqproducer.config;

import com.example.enums.RabbitEnums;
import com.wyci.utils.json.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
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

    private final static Logger log = LoggerFactory.getLogger(RabbitMQConfig.class);

//    @Autowired
//    private RabbitTemplate rabbitTemplate;


    @Bean
    public Queue defaultQueue() {
        return new Queue(RabbitEnums.DEFAULT_QUEUE, true);
    }


    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(RabbitEnums.DEFAULT_EXCHANGE, true, false);
    }


    @Bean
    public Binding bindingDirectExchange(Queue directQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue).to(directExchange).with(RabbitEnums.DEFAULT_ROUT);
    }


    /**
     * MessageConverter用于将Java对象转换为RabbitMQ的消息。 默认情况下，Spring Boot使用SimpleMessageConverter，只能发送String和byte[]类型的消息，不太方便。 使用Jackson2JsonMessageConverter，我们就可以发送JavaBean对象，由Spring Boot自动序列化为JSON并以文本消息传递。
     *
     * @return
     */
    @Bean
    MessageConverter createMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 将自定义的RabbitTemplate对象注入bean容器
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启消息推送结果回调
        rabbitTemplate.setMandatory(true);
        //设置ConfirmCallback回调
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            log.info("==============ConfirmCallback start ===============");
            log.info("回调数据：{}", correlationData);
            log.info("确认结果：{}", ack);
            log.info("返回原因：{}", cause);
            log.info("==============ConfirmCallback end =================");
        });
        //设置ReturnsCallback回调
        rabbitTemplate.setReturnsCallback(returned -> {
            log.info("==============ReturnsCallback start ===============");
            log.info("发送消息ReturnsCallback：{}", JsonUtil.objectToJson(returned));
            log.info("==============ReturnsCallback end =================");
        });

        return rabbitTemplate;
    }


}
