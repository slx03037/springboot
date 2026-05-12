package com.tools.desgin.advanced.factory.node05;

/**
 * @author shenlx
 * @description
 * @date 2024/8/15 17:01
 */
public class AbstractFactoryPatternDemo {
    public static void main(String[]args){
        //获取形状工厂
        AbstractFactory shape = FactoryProducer.getFactory("SHAPE");

        //获取形状为Circle得对象
        Shape circle = shape.getShape("CIRCLE");
        circle.draw();

        //获取形状为rectangle得对象
        Shape rectangle = shape.getShape("RECTANGLE");
        rectangle.draw();

        //获取形状为square得对象
        Shape square = shape.getShape("SQUARE");
        square.draw();

        //获取颜色工厂
        AbstractFactory color = FactoryProducer.getFactory("COLOR");

        //获取颜色为RED对象
        Color red = color.getColor("RED");
        red.fill();

        //获取颜色为GREEN对象
        Color green = color.getColor("GREEN");
        green.fill();

        //获取颜色为BLUE对象
        Color blue = color.getColor("BLUE");
        blue.fill();
    }
}
