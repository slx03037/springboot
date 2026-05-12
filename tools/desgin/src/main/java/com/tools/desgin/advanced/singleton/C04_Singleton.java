package com.tools.desgin.advanced.singleton;

/**
 * @author shenlx
 * @description
 * @date 2024/8/14 11:22
 */
public class C04_Singleton {
    //使用静态变量记录唯一实例
    private static C04_Singleton singleton=null;

    private C04_Singleton(){}

    public static synchronized C04_Singleton getInstance(){
        if(singleton==null){
            singleton=new C04_Singleton();
        }
        return singleton;
    }

    public static  C04_Singleton getInstance1(){
        synchronized (C04_Singleton.class){
            if(singleton==null){
                singleton=new C04_Singleton();
            }
            return singleton;
        }
    }
}
