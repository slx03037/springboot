package com.tools.desgin.structure.bridge.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/9/10 17:01
 */
public class UserMsg extends AbstractMsg{
    public UserMsg(MsgImplementor impl) {
        super(impl);
    }

    @Override
    public void sendMessage(String message, String toUser){
        message="尊敬的用户:"+message;
        super.sendMessage(message,toUser);
    }
}
