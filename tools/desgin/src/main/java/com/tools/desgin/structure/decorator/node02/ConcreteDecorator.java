package com.tools.desgin.structure.decorator.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/8/30 9:57
 */
public class ConcreteDecorator extends Decorator{
    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void doSomething(){
        System.out.println("业务逻辑功能扩展");
        super.doSomething();
    }
}
