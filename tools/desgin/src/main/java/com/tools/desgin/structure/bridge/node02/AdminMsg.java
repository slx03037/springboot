package com.tools.desgin.structure.bridge.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/9/10 16:58
 */
public class AdminMsg extends AbstractMsg{
    public AdminMsg(MsgImplementor implementor){
        super(implementor);
    }

    @Override
    public void sendMessage(String message, String toUser){
        message="辛苦的管理员:"+message;
        super.sendMessage(message,toUser);
    }
}
