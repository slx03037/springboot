package com.tools.desgin.behavior.meditaor.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/10/9 17:29
 */
public class MediatorPatternDemo {

    public static void main(String[]args){
        User robert=new User("Robert");

        User john=new User("John");

        robert.sendMessage("Hi John!");
        john.sendMessage("hello Robert!");
    }
}
