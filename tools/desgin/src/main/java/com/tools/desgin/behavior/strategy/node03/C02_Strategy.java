package com.tools.desgin.behavior.strategy.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/9 9:57
 */
public class C02_Strategy {
    public static void main(String[]args){
        ConcreteStrategyB concreteStrategyB = new ConcreteStrategyB();
        Context context=new Context(concreteStrategyB);
        context.userMethod();
    }
}
