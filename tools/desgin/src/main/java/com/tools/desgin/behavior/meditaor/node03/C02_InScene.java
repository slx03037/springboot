package com.tools.desgin.behavior.meditaor.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/10 16:01
 */
public class C02_InScene {
    public static void main(String[]args){
        ConcreteMediator concreteMediator = new ConcreteMediator();
        ConcreteMediatorA concreteMediatorA = new ConcreteMediatorA(concreteMediator);
        ConcreteMediatorB concreteMediatorB = new ConcreteMediatorB(concreteMediator);
        concreteMediatorA.operate();
        concreteMediatorB.operate();
    }
}
