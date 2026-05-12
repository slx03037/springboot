package com.tools.desgin.advanced.builder.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/8/21 15:00
 */
public class Director {
    private final Builder builder;

    Director(Builder builder){
        this.builder=builder;
    }

    public void construct(){
        builder.builderAct1();
        builder.builderAct2();
    }
}

