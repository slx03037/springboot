package com.tools.desgin.behavior.strategy.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/9 9:55
 */
public class ConcreteStrategyA implements Strategy{
    @Override
    public void strategyMethod() {
        System.out.println("策略方法A");
    }
}
