package com.tools.desgin.structure.flyweight.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/9/10 14:47
 */
public class C03_Integer {
    public static void main(String[]args){
        Integer c1 = Integer.valueOf(127), c2 = Integer.valueOf(127);
        Integer c3 = Integer.valueOf(127), c4 = Integer.valueOf(127);
        System.out.println(c1==c2);
        System.out.println(c3==c2);
        System.out.println(c3==c4);
        Integer c5 = Integer.valueOf(222),c6=Integer.valueOf(222);
        System.out.println(c5==c6);
    }
}
