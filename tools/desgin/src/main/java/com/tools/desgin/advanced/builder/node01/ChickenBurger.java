package com.tools.desgin.advanced.builder.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/8/21 10:49
 */
public class ChickenBurger extends Burger{
    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public float price() {
        return 60.5f;
    }
}
