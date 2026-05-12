package com.tools.desgin.behavior.strategy.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/9 9:56
 */
public class Context {
    private final Strategy strategy;

    public Context(Strategy strategy){
        this.strategy=strategy;
    }

    public void userMethod(){
        this.strategy.strategyMethod();
    }
}
