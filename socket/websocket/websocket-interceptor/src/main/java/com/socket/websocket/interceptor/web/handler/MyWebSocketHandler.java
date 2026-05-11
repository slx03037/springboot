package com.socket.websocket.interceptor.web.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shenlx
 * @description
 * @date 2025/2/27 15:06
 */
@Slf4j
@Component
public class MyWebSocketHandler implements WebSocketHandler {
    //存储所有客户端的会话WebSocketSession，key使用客户端的唯一标识方便存取
    private static final ConcurrentHashMap<String,WebSocketSession> allWebSocketSession=new ConcurrentHashMap<>();

    /**
     * 客户端成功建立连接时触发
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("客户端成功建立连接=>:{},param:{},user:{},token:{}"
                ,session.getId(),session.getAttributes().get("param")
                ,session.getAttributes().get("user")
                ,session.getAttributes().get("=token"));

        //存储所有客户端的webSocketSession
        allWebSocketSession.put((String)session.getAttributes().get("param"),session);
    }


    /**
     * 接收客户端数据时触发
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("接收客户端数据=>{}", message.getPayload());
        //给客户端回消息
        this.send(session,"hello word !!! this is websocket server"+ message.getPayload());
    }

    /**
     * 通信异常时触发
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("通信异常:{}",session.getId());
    }

    /**
     * 4.客户都安关闭连接时触发
     * @param session
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("客户端关闭连接=>{}",session.getId());
        //移除通信关闭的客户端
        allWebSocketSession.remove((String)session.getAttributes().get("param"));
    }

    /**
     * 是否支持分段传输
     * @return
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }




    //5 服务器主动发送数据
    public void send(WebSocketSession session,String msg){
        try{
            session.sendMessage(new TextMessage(msg));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 服务器主动关闭通信
     */
    public void close(WebSocketSession session,String msg){
        try{
            session.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
