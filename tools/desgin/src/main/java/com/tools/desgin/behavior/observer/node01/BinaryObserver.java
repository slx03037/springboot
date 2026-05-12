package com.tools.desgin.behavior.observer.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/9/11 14:04
 */
public class BinaryObserver extends Observer{

    public BinaryObserver(Subject subject){
        this.subject=subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Binary String "+Integer.toBinaryString(subject.getState()));
    }
}
