package com.tools.desgin.behavior.observer.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/9/11 15:31
 */
public class ConcreteSubject extends Subject{
    private String state;

    private String msgTopic;

    public String getState() {
        return state;
    }

    public String getMsgTopic() {
        return msgTopic;
    }

    public void change(String newState,String newMsgTopic){
        this.state=newState;
        this.msgTopic=newMsgTopic;
        System.out.println("主题状态:"+state);
        this.notifyObserver(msgTopic);
    }
}
