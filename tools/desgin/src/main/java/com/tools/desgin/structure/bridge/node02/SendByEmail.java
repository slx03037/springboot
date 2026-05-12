package com.tools.desgin.structure.bridge.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/9/10 17:02
 */
public class SendByEmail implements MsgImplementor{
    @Override
    public void send(String message, String toUser) {
        System.out.println("邮件通知："+toUser+";内容："+message);
    }
}
