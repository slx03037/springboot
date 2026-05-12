package com.tools.desgin.advanced.prototype.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/8/16 11:15
 */
public class PrototypePatternDemo {
    public static void main(String[] args){
        ShapeCache.loadCache();

        Shape shape0 = ShapeCache.getShape("1");
        System.out.println("Shape:"+shape0.getType());

        Shape shape1 = ShapeCache.getShape("2");
        System.out.println("Shape:"+shape1.getType());

        Shape shape2 = ShapeCache.getShape("3");
        System.out.println("Shape:"+shape2.getType());
    }
}
