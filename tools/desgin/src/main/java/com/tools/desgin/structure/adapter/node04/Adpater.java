package com.tools.desgin.structure.adapter.node04;

/**
 * @author shenlx
 * @description
 * @date 2024/8/26 15:44
 */
public class Adpater extends Adaptee implements Target{
    private final Adaptee adaptee;

    public Adpater(Adaptee adaptee){
        this.adaptee=adaptee;
    }

    @Override
    public void sampleOperation1() {
        this.adaptee.sampleOperation1();
    }

    @Override
    public void sampleOperation2() {
        System.out.println("Adpater.sampleOperation2");
    }
}
