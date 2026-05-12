package com.tools.desgin.advanced.factory.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/8/14 15:18
 */
public class C01_InScene {
    public static void main(String[]args){
        Order_Food orderFood=new Order_Food();
        orderFood.orderFood("fish");
        System.out.println("<<<<<<------------------------------------->>>>>>");
        orderFood.orderFood("chicken");
    }
}
