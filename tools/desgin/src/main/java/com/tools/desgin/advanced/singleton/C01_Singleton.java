package com.tools.desgin.advanced.singleton;

/**
 * @author shenlx
 * @description 单例模式
 * @date 2024/8/12 17:02
 */
public class C01_Singleton {
    public static final C01_Singleton C_01_SINGLETON =new C01_Singleton();

    //限制产生多个对象
    private C01_Singleton(){

    }

    //通过该方法获得实例对象
    public  static C01_Singleton getSingleton(){
        return C_01_SINGLETON;
    }

    //类中其他方法，尽量是static修饰
    public static void doSomething(){

    }

    /**
     * 使用场景
     *1. 要求生成唯一序列号
     *2. 在整个项目中需要一个共享访问点或者共享数据，例如一个web页面得计数器，可以不用把灭磁刷新都记录到数据库中
     * ，使用单例模式保持计数器得值，并确保是线程安全得
     * 3.创建一个对象选哟消耗得资源过多，如要访问IO和数据库等资源
     * 4.需要定义得静态常量和静态方法(如工具类)得环境，可以采用单例模式
     */
}
