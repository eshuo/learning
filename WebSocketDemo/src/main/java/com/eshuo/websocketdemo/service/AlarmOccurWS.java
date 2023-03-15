package com.eshuo.websocketdemo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-03-15 14:02
 * @Version V1.0
 */
@Component
@ServerEndpoint("/route/{robotId}")
@Log4j2
public class AlarmOccurWS {


    /**
     * 在线人数
     */
    private static int onlineNumber = 0;

    /**
     * 以机器人的姓名为key，WebSocket为对象保存起来
     * 保存所有在线socket连接
     */
    private static Map<String, AlarmOccurWS> clients = new ConcurrentHashMap<>();

    /**
     * 会话
     */
    private Session session;

    /**
     * 机器人ID
     */
    private String robotId;

    //处理连接建立
    @OnOpen
    public void onOpen(@PathParam("robotId") String robotId, Session session) {
        this.robotId = robotId;
        this.session = session;
        onlineNumber++;
        log.info("现在来连接的客户id：{" + session.getId() + "}，机器人id：{" + robotId + "}");
        log.info("有新连接加入！ 当前在线人数" + onlineNumber);
        if (clients.containsKey(robotId)) {
            log.info("检测到已存在该客户连接，替换为新连接...");
        }
        //把自己的信息加入到map当中去
        clients.put(robotId, this);
    }

    //接受消息
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            log.info("来自客户端消息：{" + message + "}。客户端的id是：" + robotId);
        } catch (Exception e) {
            log.info("发生了错误了");
        }
    }

    //处理错误
    @OnError
    public void onError(Session session, Throwable error) {
        log.info("服务端发生了错误" + error.getMessage());
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        onlineNumber--;
        clients.remove(robotId);

        log.info("机器人{" + robotId + "}连接关闭！当前在线人数" + onlineNumber);
    }

    //发送消息
    public void sendMessageTo(String message, String torobotId) {
        final AlarmOccurWS alarmOccurWS = clients.get(torobotId);
        if (null != alarmOccurWS) {
            alarmOccurWS.session.getAsyncRemote().sendText(message);
        }
    }

    //群发消息
    public void sendMessageAll(String message) {
        for (AlarmOccurWS item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    //操作onlineNumber，使用synchronized确保线程安全
    private static synchronized int getOnlineCount() {
        return onlineNumber;
    }
}
