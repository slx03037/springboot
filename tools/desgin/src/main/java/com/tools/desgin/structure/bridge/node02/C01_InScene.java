package com.tools.desgin.structure.bridge.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/9/10 17:03
 */
public class C01_InScene {
    public static void main(String[]args){
        MsgImplementor sendBySMS = new SendBySMS();

        AbstractMsg userMsg = new UserMsg(sendBySMS);
        userMsg.sendMessage("您的账号异步登录","用户A0001");

        SendByEmail sendByEmail = new SendByEmail();
        AbstractMsg adminMsg = new AdminMsg(sendByEmail);
        adminMsg.sendMessage("项目上线通知","运维S0001");
    }
}
