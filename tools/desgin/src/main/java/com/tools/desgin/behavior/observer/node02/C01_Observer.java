package com.tools.desgin.behavior.observer.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/9/11 14:52
 */
public class C01_Observer {
    public static void main(String[]args){
        ConcreteSubject concreteSubject = new ConcreteSubject();

        Observer observer1 = new ConcreteObserver("观察者A");

        Observer observer2 = new ConcreteObserver("观察者B");

        concreteSubject.attach(observer1);
        concreteSubject.attach(observer2);

        concreteSubject.change("new State !");

        concreteSubject.change("new State1 !");
    }
}
