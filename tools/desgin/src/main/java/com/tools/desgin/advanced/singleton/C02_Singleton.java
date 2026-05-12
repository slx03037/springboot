package com.tools.desgin.advanced.singleton;

/**
 * @author shenlx
 * @description 线程不安全实例
 * @date 2024/8/12 17:07
 */
public class C02_Singleton {
    public static C02_Singleton singleton=null;

    //限制产生多个对象
    private C02_Singleton(){

    }


    //通过改方法获得实例对象
    public static C02_Singleton getSingleton(){
        if(singleton==null){
            singleton=new C02_Singleton();
        }
        return singleton;
    }

    /**
     * 解决办法getSingleton方法前加synchronized关键字，也可以在getSingleton方法内增加synchronized来实现
     */
}
