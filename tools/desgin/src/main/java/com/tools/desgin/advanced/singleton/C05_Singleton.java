package com.tools.desgin.advanced.singleton;

/**
 * @author shenlx
 * @description
 * @date 2024/8/14 11:26
 */
public class C05_Singleton {
    //使用静态变量记录唯一实例
    private static final C05_Singleton singleton=new C05_Singleton();
    private C05_Singleton(){}

    //获取可用的对象
    public static C05_Singleton getInstance(){
        return singleton;
    }

}
