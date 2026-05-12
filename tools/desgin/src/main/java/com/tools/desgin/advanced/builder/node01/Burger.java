package com.tools.desgin.advanced.builder.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/8/21 10:41
 */
public abstract class Burger implements Item{
    @Override
    public  Packing packing(){
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
