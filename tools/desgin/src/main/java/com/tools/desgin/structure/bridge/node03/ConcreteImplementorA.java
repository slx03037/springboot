package com.tools.desgin.structure.bridge.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/9/11 11:31
 */
public class ConcreteImplementorA extends Implementor{
    @Override
    public void operationImpl() {
        System.out.println("ConcreteImplementorA.operationImpl");
    }
}
