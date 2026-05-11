package com.socket.websocket.socketio.web.cache;

import com.corundumstudio.socketio.SocketIOClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shenlx
 * @description 缓存用户 - 页面sessionId - 通道连接
 * @date 2025/3/3 11:57
 */
@Component
public class ClientCache {
    /**
     * TODO 用户信息缓存
     **/
    private static final Map<String, HashMap<UUID, SocketIOClient>> concurrentHashMap = new ConcurrentHashMap<>();

    /**
     * TODO userId-用户ID | sessionId-页面sessionId | socketIOClient-页面对应的通道连接
     * @return void
     **/
    public void saveClient(String userId,UUID sessionId,SocketIOClient socketIOClient){
        HashMap<UUID, SocketIOClient> sessionIdClientCache = concurrentHashMap.get(userId);
        if(sessionIdClientCache == null){
            sessionIdClientCache = new HashMap<>();
        }
        sessionIdClientCache.put(sessionId,socketIOClient);
        concurrentHashMap.put(userId,sessionIdClientCache);
    }

    /**
     * TODO 获取用户的页面通道信息
     **/
    public HashMap<UUID,SocketIOClient> getUserClient(String userId){
        return concurrentHashMap.get(userId);
    }

    /**
     *TODO 根据用户Id及页面sessionID删除页面通道连接
     **/
    public void deleteSessionClientByUserId(String userId,UUID sessionId){
        concurrentHashMap.get(userId).remove(sessionId);
    }

    /**
     * TODO 根据用户Id删除用户通道连接缓存 暂无使用
     **/
    public void deleteUserCacheByUserId(String userId){
        concurrentHashMap.remove(userId);
    }
}
