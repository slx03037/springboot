package com.tools.desgin.structure.decorator.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/8/30 9:55
 */
public  class ConcreteComponent implements Component{
    @Override
    public void doSomething() {
        System.out.println("处理业务逻辑");
    }
}
