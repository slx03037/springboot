package com.socket.websocket.webmvc.server.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shenlx
 * @description
 * @date 2025/2/27 15:52
 */
@Component
@Slf4j
public class ServletWebSocketServerHandler implements WebSocketHandler {
    private final static ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<String, WebSocketSession>();

    //连接建立
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println(session.getAttributes());
        String key = session.getAttributes().get("shopId").toString();
        System.out.println(key);
        if (sessions.containsKey(key)) {
            WebSocketSession oldSession = sessions.get(key);
            if (oldSession.isOpen()) {
                oldSession.close(CloseStatus.SERVICE_RESTARTED);
            }
            sessions.remove(key);
            System.out.println("closed old connection and save new connection[" + key + "]");
        }
        System.out.println("new connection[" + key + "] successfully");
        sessions.put(key, session);
        System.out.println("链接之后");
    }

    //接收消息
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("接收来自:{}的消息内容:{}", session.getId(), message.getPayload());

        System.out.println("接收到来自客户端的消息");
        if (message instanceof PongMessage) {
            session.sendMessage(new PongMessage(ByteBuffer.wrap("2".getBytes())));
        } else if (message instanceof TextMessage) {
            System.out.println("Add message into queue");
        } else if (message instanceof BinaryMessage) {
            System.out.println("Receive binary message");
        } else {
            throw new Exception("Error format");
        }
        System.out.println("接受消息ID =================   " + session.getId());
        System.out.println("接收到的消息===============   " + message);
    }

    //异常处理
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("出现异常:{}", exception.getLocalizedMessage());
        if (session.isOpen()) {
            session.close();
        }
    }

    //连接关闭
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.warn("关闭连接状态:{}，原因:{}", closeStatus.getCode(), closeStatus.getReason());
        System.out.println("Closed code {}, reason {}" +  closeStatus.getCode()+ "========="+ closeStatus.getReason());
        String key = session.getAttributes().get("shopId").toString();
        System.out.println(key);
        sessions.remove(key);
        System.out.println("链接关闭");
    }

    //是否支持接收不完整的消息
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 查询客户端是否在线
     * @param key	客户端ID
     * @return
     */
    public static boolean isConnected(String key){
        if (sessions.containsKey(key)) {
            WebSocketSession session = sessions.get(key);
            if(null != session && session.isOpen()) {
                return true;
            }
            System.out.println("connection[" + key + "] closed");
        }else {
            System.out.println("connection[" + key + "] not found");
        }
        return false;
    }

    /**
     * 发送消息,如果连接断开，缓存到数据库
     * @param key 		客户端ID
     * @param content	发送内容
     */
    public void sendMessage(String key, String content){
        System.out.println("send content>>> " + content);
        if (isConnected(key)) {
            try {
                sessions.get(key).sendMessage(new TextMessage(content));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            //cache
            System.out.println("Send failure, connection [" + key + "] interrupt ");
        }
    }


}
