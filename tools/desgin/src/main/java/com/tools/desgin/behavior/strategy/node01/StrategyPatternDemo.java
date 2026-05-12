package com.tools.desgin.behavior.strategy.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/10/8 15:37
 */
public class StrategyPatternDemo {

    public static void main(String[]args){
        Context context = new Context(new OperationAdd());

        System.out.println("10+5="+context.executeStrategy(10,5));

        context = new Context(new OperationSubtract());

        System.out.println("10 - 5="+context.executeStrategy(10,5));

        context = new Context(new OperationMultiply());

        System.out.println("10 * 5="+context.executeStrategy(10,5));
    }
}
