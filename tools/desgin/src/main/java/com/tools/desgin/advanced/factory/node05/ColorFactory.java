package com.tools.desgin.advanced.factory.node05;

/**
 * @author shenlx
 * @description
 * @date 2024/8/15 16:59
 */
public class ColorFactory extends AbstractFactory{
    @Override
    public Color getColor(String color) {
        if(color ==null){
            return null;
        }
        if(color.equalsIgnoreCase("RED")){
            return new Red();
        } else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        } else if(color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        return null;
    }
}
