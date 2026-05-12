package com.tools.desgin.structure.flyweight.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/9/10 14:42
 */
public class C02_Flyweight {
    public static void main(String[]args){
        FlyweightFactory factory=new FlyweightFactory();
        Flyweight c = factory.factoryMethod('c');
        c.printState("One");
        Flyweight d = factory.factoryMethod('d');
        d.printState("Two");
        Flyweight c1 = factory.factoryMethod('c');
        c1.printState("Three");
        factory.poolInfo();

    }
}
