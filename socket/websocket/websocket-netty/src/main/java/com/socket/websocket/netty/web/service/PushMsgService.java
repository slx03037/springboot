package com.socket.websocket.netty.web.service;

/**
 * @author shenlx
 * @description
 * @date 2025/1/21 15:29
 */
public interface PushMsgService {
    /**
     * 推送给指定用户
     * @param userId
     * @param msg
     */
    void pushMsgToOne(String userId,String msg);

    /**
     * 推送给所有用户
     * @param msg
     */
    void pushToAll(String msg);
}
