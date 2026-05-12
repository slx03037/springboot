package com.tools.desgin.structure.decorator.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/8/27 14:24
 */
public abstract class AbstractShapeDecorator implements Shape{
    public Shape decoratorShape;

    public AbstractShapeDecorator(Shape decoratorShape){
        this.decoratorShape=decoratorShape;
    }

    @Override
    public void draw(){
        decoratorShape.draw();
    }
}
