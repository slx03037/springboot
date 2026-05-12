package com.tools.desgin.advanced.singleton;

/**
 * @author shenlx
 * @description
 * @date 2024/8/14 14:04
 */
public class C08_Singleton {

    public static void main(String[]args){
        SingletonObject instance = SingletonObject.INSTANCE;
        SingletonObject instance1 = SingletonObject.INSTANCE;
        System.out.println(instance==instance1);
    }
    enum SingletonObject{
        INSTANCE;
        public void info(){
            System.out.println("hello,world");
        }
    }
}
