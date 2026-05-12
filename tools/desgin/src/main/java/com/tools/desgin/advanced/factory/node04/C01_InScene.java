package com.tools.desgin.advanced.factory.node04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author shenlx
 * @description
 * @date 2024/8/15 15:33
 */
public class C01_InScene {
    public static void main(String[]args){
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring-factorymethod.xml");
        CarEntity car1 = (CarEntity)context.getBean("car1");
        CarEntity car2 = (CarEntity)context.getBean("car2");
        System.out.println(car1);
        System.out.println(car2);
    }
}
