package com.component.bean.interfaces;

import com.component.bean.interfaces.node01.beandefinitionregistrypostprocessor.d1.Apple;
import com.component.bean.interfaces.node01.beandefinitionregistrypostprocessor.d1.Orange;
import com.component.bean.interfaces.node01.beanfactorypostprocessor.d1.Blue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author shenlx
 * @description
 * @date 2025/2/21 16:09
 */
@SpringBootApplication
public class Application {
    public static void main(String[]args){
//        SpringApplication springApplication = new SpringApplication(Application.class);
//        springApplication.addInitializers(new TestApplicationContextInitializer());
//        System.out.println("new TestApplicationContextInitializer");
//        springApplication.run(args);

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("com.xinwen.start.node01");
        Blue blue = ctx.getBean(Blue.class);
        System.out.println(blue);

        Apple apple = ctx.getBean(Apple.class);
        System.out.println(apple);
        Orange orange = ctx.getBean(Orange.class);
        System.out.println(orange);
        SpringApplication.run(Application.class,args);
    }
}
