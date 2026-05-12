package com.tools.desgin.behavior.strategy.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/9 9:48
 */
public class Payment {
    private final FullReduce fullReduce;

    public Payment(FullReduce fullReduce){
        this.fullReduce=fullReduce;
    }

    public double payment(Double totalPrice){
        return this.fullReduce.getPayMoney(totalPrice);
    }

}
