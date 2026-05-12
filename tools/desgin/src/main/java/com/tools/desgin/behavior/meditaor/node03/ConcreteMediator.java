package com.tools.desgin.behavior.meditaor.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/10 14:41
 */
public class ConcreteMediator implements Mediator{

    private ConcreteMediatorA concreteMediatorA;

    private ConcreteMediatorB concreteMediatorB;

    public void setConcreteMediatorA(ConcreteMediatorA concreteMediatorA) {
        this.concreteMediatorA = concreteMediatorA;
    }

    public void setConcreteMediatorB(ConcreteMediatorB concreteMediatorB) {
        this.concreteMediatorB = concreteMediatorB;
    }

    @Override
    public void notify(Colleague colleague) {
        System.out.println("协调通知消息");
    }
}
