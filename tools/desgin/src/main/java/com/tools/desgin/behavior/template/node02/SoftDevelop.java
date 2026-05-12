package com.tools.desgin.behavior.template.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/9/24 11:47
 */
public abstract class SoftDevelop {

    public final void templateMethod(){
        doBiz();
        doProduct();
        doDevelop();
        doTest();
    }

    public abstract void doBiz();

    public abstract void doProduct();

    public abstract void doDevelop();

    public abstract void doTest();
}
