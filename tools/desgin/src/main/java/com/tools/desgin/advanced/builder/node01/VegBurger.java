package com.tools.desgin.advanced.builder.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/8/21 10:43
 */
public class VegBurger extends Burger{
    @Override
    public String name() {
        return "Veg Burger";
    }


    @Override
    public float price() {
        return 59.5f;
    }
}
