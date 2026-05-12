package com.tools.desgin.advanced.singleton;

/**
 * @author shenlx
 * @description
 * @date 2024/8/14 11:35
 */
public class C06_Singleton {
    //使用静态变量记录唯一实例
    //volatile可以确保当Singleton被初始化后，多线程才可以正确处理
    //被volatile修饰的变量的值，将不会被本地线程缓存
    //对该变量读写都是直接操作共享内存，确保多个线程能正确的处理该变量
    private static volatile C06_Singleton singleton=null;

    private C06_Singleton(){}

    public static C06_Singleton getInstance(){
        //如果实例不存在，则进入不同步社区
        if(singleton==null){
            synchronized (C06_Singleton.class){
                singleton=new C06_Singleton();
            }
        }
        return singleton;
    }
}
