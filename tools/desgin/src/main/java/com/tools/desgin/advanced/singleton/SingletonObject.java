package com.tools.desgin.advanced.singleton;

/**
 * @author shenlx
 * @description
 * @date 2024/8/14 11:15
 */
public class SingletonObject {
    //创建SingletonObject的一个对象
    private static final SingletonObject instance=new SingletonObject();

    //让构造函数为private，这样该类就不会被实例化
    private SingletonObject(){}

    //获取唯一可用的对象
    public static SingletonObject getInstance(){
        return instance;
    }

    public void showMessage(){
        System.out.println("hello world");
    }
}
