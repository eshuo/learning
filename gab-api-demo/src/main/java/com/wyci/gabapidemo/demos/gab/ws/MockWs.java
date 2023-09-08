package com.wyci.gabapidemo.demos.gab.ws;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-09-08 16:50
 * @Version V1.0
 */
@Component
@ServerEndpoint("/webSocket/{userId}")
@Log4j2
public class MockWs {


    /**
     * 在线人数
     */
    private static int onlineNumber = 0;

    /**
     * 以机器人的姓名为key，WebSocket为对象保存起来
     * 保存所有在线socket连接
     */
    private static Map<String, MockWs> clients = new ConcurrentHashMap<>();

    /**
     * 会话
     */
    private Session session;

    /**
     * 机器人ID
     */
    private String userId;

    //处理连接建立
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) {
        this.userId = userId;
        this.session = session;
        onlineNumber++;
        log.info("现在来连接的客户id：{" + session.getId() + "}，用户id：{" + userId + "}");
        log.info("有新连接加入！ 当前在线人数" + onlineNumber);
        if (clients.containsKey(userId)) {
            log.info("检测到已存在该客户连接，替换为新连接...");
        }
        //把自己的信息加入到map当中去
        clients.put(userId, this);
    }


    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        onlineNumber--;
        clients.remove(userId);

        log.info("用户{" + userId + "}连接关闭！当前在线人数" + onlineNumber);
    }


    /**
     * 发送消息
     * @param message
     * @param userId
     */
    public void sendMessageTo(String message, String userId) {
        final MockWs mockWs = clients.get(userId);
        if (null != mockWs) {
            mockWs.session.getAsyncRemote().sendText(message);
        }
    }


    /**
     * 群发消息
     * @param message
     */
    public void sendMessageAll(String message) {
        for (MockWs item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }
}
