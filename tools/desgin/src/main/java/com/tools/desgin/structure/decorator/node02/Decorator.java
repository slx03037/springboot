package com.tools.desgin.structure.decorator.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/8/30 9:56
 */
public abstract class Decorator implements Component{
    private final Component component;
    public Decorator(Component component) {
        this.component=component;
    }

    @Override
    public void doSomething() {
        component.doSomething();
    }
}
