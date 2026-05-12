package com.tools.desgin.advanced.prototype.node02;

import java.util.StringJoiner;

/**
 * @author shenlx
 * @description
 * @date 2024/8/16 14:21
 */
public class Product implements Cloneable{
    private final String name;

    private final String color;

    private final Double price;

    public Product(String name,String color,Double price){
        this.name=name;
        this.color=color;
        this.price=price;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("color='" + color + "'")
                .add("price=" + price)
                .toString();
    }

    @Override
    public Object clone() {
        try {
            Product clone = (Product) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
