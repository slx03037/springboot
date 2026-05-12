package com.tools.desgin.advanced.builder.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/8/21 16:17
 */
public class C02_Builder {
    public static void main(String[]args){
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        director.construct();

        Product product = builder.builderProduct();
        System.out.println(product.getAct1());
        System.out.println(product.getAct2());
    }
}
