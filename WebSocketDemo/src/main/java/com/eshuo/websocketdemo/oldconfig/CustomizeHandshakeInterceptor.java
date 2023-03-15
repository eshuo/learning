package com.eshuo.websocketdemo.oldconfig;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-03-15 11:29
 * @Version V1.0
 */
//@Component
public class CustomizeHandshakeInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
//       用户信息等有用信息可存储在 Map<String, Object> attributes 中，在 handler 中可使用 WebSocketSession#getAttributes() 方法取出相应的数据。
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
