package com.tools.desgin.structure.bridge.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/9/11 11:33
 */
public abstract class Abstraction {
    private final Implementor implementor;

    public Abstraction(Implementor implementor){
        this.implementor=implementor;
    }

    public void operation(){
        implementor.operationImpl();
    }
}
