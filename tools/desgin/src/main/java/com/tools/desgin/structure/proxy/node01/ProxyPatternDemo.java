package com.tools.desgin.structure.proxy.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/8/21 16:50
 */
public class ProxyPatternDemo {
    public static void main(String[]args){
        Image image=new ProxyImage("TEST_10MB.JPG");
        //从磁盘加载图像
        image.display();
        System.out.println("-------------");
        //不从磁盘加载图像
        image.display();
    }
}
