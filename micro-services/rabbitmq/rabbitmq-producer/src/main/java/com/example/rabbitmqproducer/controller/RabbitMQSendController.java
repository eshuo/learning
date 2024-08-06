package com.example.rabbitmqproducer.controller;

import com.example.enums.RabbitEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author wangshuo
 * @Date 2024-08-06 15:18
 * @Version V1.0
 */
@RestController
@RequestMapping("/rabbitmq")
public class RabbitMQSendController {

    private final static Logger logger = LoggerFactory.getLogger(RabbitMQSendController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @GetMapping("/default/queue/{msg}")
    @ResponseBody
    public String defaultQueue( @PathVariable("msg") String msg) {
        logger.error("defaultQueue msg:{}", msg);
//        logger.error("defaultQueue httpInfo:{}", HttpLogFormatterUtils.requestInfo(ServletUtils.getRequest()));
        rabbitTemplate.convertAndSend(RabbitEnums.DEFAULT_EXCHANGE, RabbitEnums.DEFAULT_ROUT, msg);
        return "OK";
    }


}
