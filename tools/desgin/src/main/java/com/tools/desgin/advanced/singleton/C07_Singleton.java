package com.tools.desgin.advanced.singleton;

/**
 * @author shenlx
 * @description
 * @date 2024/8/14 11:40
 */
public class C07_Singleton {
    //静态内部类
    private static class C07_Singleton_Holder{
        private static final C07_Singleton singleton=new C07_Singleton();

    }
    private C07_Singleton(){}

    public static final C07_Singleton getInstance(){
        return C07_Singleton_Holder.singleton;
    }
}
