package com.eshuo.websocketdemo.oldconfig;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-03-15 11:27
 * @Version V1.0
 */
//@Component
public class CustomizeWebSocketHandler implements WebSocketHandler {
    /**
     * 建立连接
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 缓存用户信息: userInfo
    }

    /**
     * 接收消息
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
    }

    /**
     * 发生错误
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // 清除用户缓存信息
    }

    /**
     * 关闭连接
     * @param session
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        // 清除用户缓存信息
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
