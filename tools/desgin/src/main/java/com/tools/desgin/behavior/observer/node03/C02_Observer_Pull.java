package com.tools.desgin.behavior.observer.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/9/11 15:37
 */
public class C02_Observer_Pull {
    public static void main(String[]args){
        ConcreteSubject subject = new ConcreteSubject();
        Observer observer1=new ConcreteObserver("观察者A","java");
        Observer observer2=new ConcreteObserver("观察者B","mysql");
        subject.attach(observer1);
        subject.attach(observer2);

        subject.change("java state!","java");

        subject.change("mysql state!","mysql");
    }
}
