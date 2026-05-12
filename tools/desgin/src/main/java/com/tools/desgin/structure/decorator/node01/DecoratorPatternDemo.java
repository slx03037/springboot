package com.tools.desgin.structure.decorator.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/8/27 15:31
 */
public class DecoratorPatternDemo {
    public static void main(String[]args){
        Shape circle = new Circle();
        AbstractShapeDecorator abstractRedShapeDecorator = new AbstractRedShapeDecorator(new Circle());
        AbstractShapeDecorator shapeDecorator = new AbstractRedShapeDecorator(new Rectangle());
        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("\n circle of red border");
        abstractRedShapeDecorator.draw();

        System.out.println("\n rectangle of red border");
        shapeDecorator.draw();
    }
}
