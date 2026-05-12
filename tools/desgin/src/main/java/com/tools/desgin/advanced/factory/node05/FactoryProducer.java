package com.tools.desgin.advanced.factory.node05;

/**
 * @author shenlx
 * @description
 * @date 2024/8/15 17:00
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("SHAPE")){
            return new ShapeFactory();
        }else if(choice.equalsIgnoreCase("COLOR")){
            return new ColorFactory();
        }
        return null;
    }
}
