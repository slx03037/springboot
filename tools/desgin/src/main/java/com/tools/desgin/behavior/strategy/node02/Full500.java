package com.tools.desgin.behavior.strategy.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/9 9:50
 */
public class Full500 implements FullReduce{
    @Override
    public double getPayMoney(Double totalPrice) {
        if(totalPrice>=500){
            totalPrice=totalPrice-120.0;
        }
        return totalPrice;
    }
}
