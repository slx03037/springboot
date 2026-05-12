package com.tools.desgin.basic.node05;

/**
 * @author shenlx
 * @description
 * @date 2024/8/13 16:17
 */
public class C01_BookPrice {
    public static void main(String[] args) {
        ParityBook parityBook = new DiscountBook("Java",100.00) ;
        System.out.println(parityBook.getPrice());
    }
}
