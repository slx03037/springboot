package com.tools.desgin.behavior.observer.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/9/11 14:07
 */
public class ObserverPatterDemo {
    public static void main(String[]args){
        Subject subject = new Subject();
        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change:15");
        subject.setState(15);

        System.out.println("Second state change:10");
        subject.setState(10);
    }
}
