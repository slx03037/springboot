package com.tools.desgin.behavior.observer.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/9/11 14:50
 */
public class ConcreteObserver implements Observer{

    private final String name;

    private String observerState;

    public ConcreteObserver(String name){
        this.name=name;
    }

    @Override
    public void update(String state) {
        observerState=state;

        System.out.println("["+this.name+"]状态: "+observerState);
    }
}
