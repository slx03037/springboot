package com.tools.desgin.structure.facade.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/8/23 11:22
 */
public class Payment {
    private static final Payment payment=new Payment();

    public static Payment getInstance(){
        return payment;
    }

    public void payMoney(){
        System.out.println("结账。。。");
    }
}
