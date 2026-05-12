package com.tools.desgin.structure.facade.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/8/23 11:50
 */
public class Facade {
    public void ClientNeed1(){
        ModuleA moduleA = new ModuleA();
        moduleA.testA();

        ModuleB moduleB = new ModuleB();
        moduleB.testB();
    }

    public void ClientNeed2(){
        ModuleB moduleB = new ModuleB();
        moduleB.testB();
        ModuleC moduleC = new ModuleC();
        moduleC.testC();
    }
}
