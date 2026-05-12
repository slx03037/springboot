package com.tools.desgin.basic.node05;

/**
 * @author shenlx
 * @description
 * @date 2024/8/13 16:18
 */
public class DiscountBook extends ParityBook{
    public DiscountBook(String name, Double price) {
        super(name, price);
    }
    @Override
    public Double getPrice() {
        double oldPrice = super.getPrice();
        return oldPrice * 0.8 ;
    }
}
