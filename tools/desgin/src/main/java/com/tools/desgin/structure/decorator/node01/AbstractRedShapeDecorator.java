package com.tools.desgin.structure.decorator.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/8/27 14:26
 */
public class AbstractRedShapeDecorator extends AbstractShapeDecorator{
    public AbstractRedShapeDecorator(Shape decoratorShape) {
        super(decoratorShape);
    }

    @Override
    public void draw(){
        decoratorShape.draw();
        setRedBorder(decoratorShape);
    }

    private void setRedBorder(Shape decoratorShape){
        System.out.println("Border Color:Red");
    }
}
