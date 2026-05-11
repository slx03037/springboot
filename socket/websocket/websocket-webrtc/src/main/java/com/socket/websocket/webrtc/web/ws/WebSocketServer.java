package com.socket.websocket.webrtc.web.ws;


import com.socket.websocket.webrtc.web.config.GetHttpSessionConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shenlx
 * @description
 * @date 2025/3/2 0:31
 */
@Component  // 表明这个类是Spring组件，由Spring管理
@ServerEndpoint(value = "/video", configurator = GetHttpSessionConfig.class) // 定义WebSocket服务器端点
@Slf4j  // Lombok库的注解，提供日志记录功能
public class WebSocketServer {

    private static final ConcurrentHashMap<String, WebSocketServer> clientsMap = new ConcurrentHashMap<>();
    private Session session;  // WebSocket会话
    private String userName;  // 用户名

    // 当客户端打开连接时调用
    @OnOpen
    public void open(Session session, EndpointConfig config) {
        userName = (String) config.getUserProperties().get("userName");  // 从配置中获取用户名
        if (userName != null) {
            log.info(userName + " 连接成功");  // 日志记录用户连接成功
            clientsMap.put(userName, this);  // 将用户加入到客户端映射中
            this.session = session;  // 保存会话
        } else {
            log.info("未知用户尝试连接，但未提供用户名");  // 日志记录未提供用户名的连接尝试
        }
    }

    // 当客户端关闭连接时调用
    @OnClose
    public void close() {
        if (userName != null) {
            clientsMap.remove(userName);  // 从映射中移除用户
            log.info(userName + " 断开连接");  // 日志记录用户断开连接
        }
    }

    // 连接发生错误时调用
    @OnError
    public void error(Throwable error) {
        log.error(userName + " 连接发生错误", error);  // 记录错误日志
    }

    // 处理从客户端接收的消息
    @OnMessage
    public void getMessage(String message) {
        log.info("收到来自 " + userName + " 的消息: " + message);  // 记录接收到的消息
        clientsMap.forEach((key, value) -> {
            if (!key.equals(this.userName)) {  // 不向消息的发送者发送消息
                try {
                    value.send(message);  // 调用send方法发送消息
                    log.info("消息已转发给 " + key);  // 记录消息转发行为
                } catch (IOException e) {
                    log.error("向 " + key + " 发送消息失败", e);  // 记录发送失败的日志
                }
            }
        });
    }

    // 向客户端发送消息的方法
    private void send(String message) throws IOException {
        if (session != null && session.isOpen()) {
            session.getBasicRemote().sendText(message);  // 发送消息
            log.info("发送消息给 " + userName + ": " + message);  // 记录发送消息的日志
        } else {
            log.info(userName + " 不在线");  // 记录用户不在线的情况
        }
    }

    // 获取当前连接数
    public int getConnectNum() {
        log.info("当前连接数: " + clientsMap.size());  // 记录当前连接数
        return clientsMap.size();  // 返回当前的客户端数量
    }
}
