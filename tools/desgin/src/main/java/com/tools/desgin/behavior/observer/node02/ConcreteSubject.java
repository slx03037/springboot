package com.tools.desgin.behavior.observer.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/9/11 14:48
 */
public class ConcreteSubject extends Subject{

    private String state;

    public String getState() {
        return state;
    }

    public void change(String newState){
        state=newState;
        System.out.println("主题状态:"+state);
        this.notifyObserver(state);
    }
}
