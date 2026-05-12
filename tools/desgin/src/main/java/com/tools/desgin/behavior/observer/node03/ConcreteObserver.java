package com.tools.desgin.behavior.observer.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/9/11 15:29
 */
public class ConcreteObserver implements Observer{

    private final String name;

    private final String msgTopic;

    private String observerState;

    public ConcreteObserver(String name,String msgTopic){
        this.name=name;
        this.msgTopic=msgTopic;
    }

    @Override
    public void update(Subject sub) {
        ConcreteSubject subject=  (ConcreteSubject)sub;
        //observerState=state;
       // System.out.println("["+this.name+"];状态："+observerState);
        if(subject.getMsgTopic().equals(msgTopic)){
            observerState = subject.getState();
            System.out.println("["+this.name+"];状态："+observerState);
        }
    }
}
