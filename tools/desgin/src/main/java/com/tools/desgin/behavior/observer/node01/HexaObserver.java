package com.tools.desgin.behavior.observer.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/9/11 14:06
 */
public class HexaObserver extends Observer{

    public HexaObserver(Subject subject){
        this.subject=subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Hex String "+Integer.toHexString(subject.getState()).toUpperCase());
    }
}
