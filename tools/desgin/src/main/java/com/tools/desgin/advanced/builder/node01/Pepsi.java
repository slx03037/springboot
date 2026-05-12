package com.tools.desgin.advanced.builder.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/8/21 10:52
 */
public class Pepsi extends ColdDrink{
    @Override
    public String name() {
        return "Pepsi";
    }

    @Override
    public float price() {
        return 32.2f;
    }
}
