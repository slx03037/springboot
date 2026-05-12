package com.tools.desgin.basic.node03;


/**
 * @author shenlx
 * @description
 * @date 2024/8/13 15:46
 */
public class C01_FarmFactory {
    public static void main(String[] args) {
        Animal animal = new Dog() ;
        FarmFactory farm = new Farming() ;
        farm.breed(animal) ;
        animal = new Pig() ;
        farm.breed(animal) ;
    }
}
