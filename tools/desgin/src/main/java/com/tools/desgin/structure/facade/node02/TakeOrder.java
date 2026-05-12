package com.tools.desgin.structure.facade.node02;

import javax.swing.plaf.PanelUI;

/**
 * @author shenlx
 * @description
 * @date 2024/8/23 11:21
 */
public class TakeOrder {
    private static final TakeOrder takeOrder=new TakeOrder();

    public static TakeOrder getInstance(){
        return takeOrder;
    }

    public void orderDishes(){
        System.out.println("点餐。。。");
    }
}
