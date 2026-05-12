package com.tools.desgin.behavior.observer.node02;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2024/9/11 14:45
 */
    public abstract class Subject {
    private final List<Observer> list=new ArrayList<>();

    public void attach(Observer observer){
        list.add(observer);

        System.out.println("注册一个观察者: "+observer.getClass().getName());
    }

    public void delete(Observer observer){
        list.remove(observer);
        System.out.println("删除一个观察者："+observer);
    }

    public void notifyObserver(String newState){
        for(Observer observer:list){
            observer.update(newState);
        }
    }
}
