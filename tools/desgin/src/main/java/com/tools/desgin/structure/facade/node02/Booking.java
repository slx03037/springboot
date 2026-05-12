package com.tools.desgin.structure.facade.node02;

import javax.swing.plaf.PanelUI;

/**
 * @author shenlx
 * @description
 * @date 2024/8/23 11:19
 */
public class Booking {
    private static final Booking booking=new Booking();

    public static Booking getInstance(){
        return booking;
    }

    public void bookPlace(){
        System.out.println("位置预定。。。");
    }
}
