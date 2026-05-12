package com.tools.desgin.structure.facade.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/8/23 11:03
 */
public class ShapeMaker {
    private final Shape circle;

    private final Shape rectangle;

    private final Shape square;

    public ShapeMaker(){
        circle=new Circle();
        rectangle=new Rectangle();
        square=new Square();
    }

    public void drawCircle(){
        circle.draw();
    }

    public void rectangleCircle(){
        rectangle.draw();
    }

    public void squareCircle(){
        square.draw();
    }
}
