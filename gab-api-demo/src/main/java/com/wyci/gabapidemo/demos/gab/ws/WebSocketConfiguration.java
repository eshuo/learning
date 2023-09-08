package com.wyci.gabapidemo.demos.gab.ws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Description  开启WebSocket支持
 * @Author wangshuo
 * @Date 2023-03-15 13:57
 * @Version V1.0
 */
@Configuration
public class WebSocketConfiguration {


    @Bean
    public ServerEndpointExporter handlerAdapter() {
        return new ServerEndpointExporter();
    }

}
