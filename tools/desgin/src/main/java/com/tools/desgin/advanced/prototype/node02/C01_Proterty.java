package com.tools.desgin.advanced.prototype.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/8/16 14:30
 */
public class C01_Proterty {
    public static void main(String[]args){
        Product product0 = new Product("机械键盘", "白色", 100.0);
        Product clone1 = (Product) product0.clone();
        Product clone2 = (Product) product0.clone();
        System.out.println(clone1==clone2);
        System.out.println(clone1==product0);
        System.out.println(clone2==product0);
    }
}
