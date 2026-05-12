package com.tools.desgin.basic.node05;

/**
 * @author shenlx
 * @description
 * @date 2024/8/13 16:17
 */
public class ParityBook implements Book{
    private final String name ;
    private final Double price ;
    public ParityBook(String name, Double price) {
        this.name = name;
        this.price = price;
    }
    @Override
    public String getName() {
        return this.name ;
    }
    @Override
    public Double getPrice() {
        return this.price ;
    }
}
