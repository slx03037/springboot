package com.tools.desgin.behavior.observer.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/9/11 14:05
 */
public class OctalObserver extends Observer{

    public OctalObserver(Subject subject){
        this.subject=subject;
        this.subject.attach(this);
    }
    @Override
    public void update() {
        System.out.println("Octal String "+Integer.toOctalString(subject.getState()));
    }
}
