package com.tools.desgin.basic.node03;



/**
 * @author shenlx
 * @description
 * @date 2024/8/13 15:47
 */
public class Farming implements FarmFactory {
    @Override
    public void breed(Animal animal) {
        System.out.println("农场饲养："+animal.getAnimalName());
    }
}
