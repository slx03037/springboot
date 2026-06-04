package com.component.bean.interfaces.node01.beanpostprocessor.node01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shenlx
 * @description 把Dog类注册到Spring容器中，并设置了Bean实例化后的初始化方法；
 * @date 2025/3/3 15:05
 */
@Configuration
public class SpringConfig {
    @Bean(initMethod = "init",name = "dog")
    public Dog dog(){
        return new Dog();
    }
}
