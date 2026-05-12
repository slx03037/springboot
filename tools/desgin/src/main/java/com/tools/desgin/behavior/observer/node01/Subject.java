package com.tools.desgin.behavior.observer.node01;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2024/9/11 14:01
 */
public class Subject {
    private final List<Observer> observers=new ArrayList<>();

    private int state;

    public int getState() {
        return state;

    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for(Observer observer:observers){
            observer.update();
        }
    }
}
