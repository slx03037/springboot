package com.tools.desgin.behavior.strategy.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/9 9:52
 */
public class C01_Inscene {
    public static void main(String[]args){
        //选择满减策略，走相应的计算方式
        FullReduce full00 = new Full00();
        Payment payment = new Payment(full00);
        double payment1 = payment.payment(300.0);
        System.out.println("最终价格为："+payment1);
    }
}
