package com.tools.desgin.behavior.template.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/8 15:14
 */
public abstract class AbstractTemplate {

    public void templateMethod(){
        abstractMethod();
        hookMethod();
        concreteMethod();
    }

    protected abstract void abstractMethod();
    protected void hookMethod(){}

    private final void concreteMethod(){}
}
