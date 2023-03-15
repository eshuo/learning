package com.eshuo.websocketdemo.oldconfig;

import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-03-15 11:24
 * @Version V1.0
 */
//@Configuration
//@EnableWebSocket//开启 websocket 功能
public class WebSocketConfig implements WebSocketConfigurer {

    @Resource
    private CustomizeWebSocketHandler customizeWebSocketHandler;

    @Resource
    private CustomizeHandshakeInterceptor customizeHandshakeInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(customizeWebSocketHandler) // 添加处理器
                .addInterceptors(customizeHandshakeInterceptor) // 添加拦截器
                .setAllowedOrigins("*"); // 解决跨域问题 [4]
    }
}
