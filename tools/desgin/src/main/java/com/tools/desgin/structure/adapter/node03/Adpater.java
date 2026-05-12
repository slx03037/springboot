package com.tools.desgin.structure.adapter.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/8/26 15:33
 */
public class Adpater extends Adaptee implements  Target{
    @Override
    public void sampleOperation2() {
        System.out.println("Adpater.sampleOperation2");
    }
}
