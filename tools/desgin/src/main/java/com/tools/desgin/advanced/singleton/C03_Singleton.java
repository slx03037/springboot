package com.tools.desgin.advanced.singleton;

/**
 * @author shenlx
 * @description
 * @date 2024/8/14 11:10
 */
public class C03_Singleton {
    //使用静态变量记录唯一实例
    private static C03_Singleton singleton=null;

    private C03_Singleton(){}

    public static C03_Singleton getInstance(){
        if(singleton==null){
            singleton=new C03_Singleton();
        }
        return singleton;
    }

    public static void main(String[]args){
        C03_Singleton singleton1=C03_Singleton.getInstance();
        C03_Singleton singleton2=C03_Singleton.getInstance();
        System.out.println(singleton1==singleton2);
    }
}
