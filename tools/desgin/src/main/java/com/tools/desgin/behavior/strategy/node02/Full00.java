package com.tools.desgin.behavior.strategy.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/9 9:49
 */
public class Full00 implements FullReduce{
    @Override
    public double getPayMoney(Double totalPrice) {
        if (totalPrice >=100){
            totalPrice=totalPrice-20.0;
        }
        return totalPrice;
    }
}
