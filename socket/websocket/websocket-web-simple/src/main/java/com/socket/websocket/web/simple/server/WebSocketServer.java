package com.socket.websocket.web.simple.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shenlx
 * @description @ServerEndpoint("/webSocket/{uid}") 前端通过此URI与后端建立链接
 * @date 2024/9/24 9:58
 */
@ServerEndpoint("/webSocket/{uid}")
@Component
@Slf4j
public class WebSocketServer {

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static final AtomicInteger onlineNum = new AtomicInteger(0);

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
    private static final CopyOnWriteArraySet<Session> sessionPools = new CopyOnWriteArraySet<Session>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "uid")String uid){
        sessionPools.add(session);
        onlineNum.incrementAndGet();
        log.info("{}--加入websocket!当前人数为:{}",uid,onlineNum);
    }

    /**
     * 连接关闭得方法
     * @param session
     */
    @OnClose
    public void onClose(Session session){
        sessionPools.remove(session);
        int cnt=onlineNum.decrementAndGet();
        log.info("有连接关闭，当前连接数：{}",cnt);
    }


    /**
     * 发送消息
     * @param session
     * @param message
     * @throws IOException
     */
    public void sendMessage(Session session,String message) throws IOException {
        if (session !=null){
            synchronized(session){
                session.getBasicRemote().sendText(message);
            }
        }
    }


    public void broadCastInfo(String message) throws IOException {
        for(Session session:sessionPools){
            if(session.isOpen()){
                sendMessage(session,message);
            }
        }
    }

    @OnError
    public void onError(Session session,Throwable throwable){
        log.error("发送错误");
        throwable.printStackTrace();
    }

}
