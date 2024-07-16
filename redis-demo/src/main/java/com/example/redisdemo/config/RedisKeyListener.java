//package com.example.redisdemo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.redis.connection.Message;
//import org.springframework.data.redis.connection.MessageListener;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.listener.PatternTopic;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.stereotype.Component;
//
///**
// * @Description redis 监听
// * @Author wangshuo
// * @Date 2024-01-23 17:13
// * @Version V1.0
// */
//@Component
//public class RedisKeyListener implements MessageListener {
//
//
//    @Bean
//    public RedisMessageListenerContainer listenerContainer(RedisConnectionFactory redisConnection, RedisKeyListener redisKeyListener) {
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        // 设置Redis的连接工厂
//        container.setConnectionFactory(redisConnection);
//        // 设置监听器
//        container.addMessageListener(redisKeyListener, new PatternTopic("__keyevent@*"));
//        return container;
//    }
//
//
//    /**
//     * Callback for processing received objects through Redis.
//     *
//     * @param message message must not be {@literal null}.
//     * @param pattern pattern matching the channel (if specified) - can be {@literal null}.
//     */
//    @Override
//    public void onMessage(Message message, byte[] pattern) {
//        String key = new String(message.getBody());//set,expired,del
//        String type = new String(message.getChannel());//__keyspace@0__:house:floor:cc
//        System.err.println(key);
//        System.err.println(type);
//    }
//}