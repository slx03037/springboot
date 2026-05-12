package com.tools.desgin.advanced.builder.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/8/21 10:42
 */
public abstract class ColdDrink implements Item{

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price() ;
}
