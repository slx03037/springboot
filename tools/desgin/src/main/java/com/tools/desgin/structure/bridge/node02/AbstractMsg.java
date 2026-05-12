package com.tools.desgin.structure.bridge.node02;

/**
 * @author shenlx
 * @description 封装消息类型
 * @date 2024/9/10 16:56
 */
public abstract class AbstractMsg {
    MsgImplementor implementor;

    public AbstractMsg(MsgImplementor impl){
        this.implementor=impl;
    }

    public void sendMessage(String message,String toUser){
        this.implementor.send(message,toUser);
    }
}
