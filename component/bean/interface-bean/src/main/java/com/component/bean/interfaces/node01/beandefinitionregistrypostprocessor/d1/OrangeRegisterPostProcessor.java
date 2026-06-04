package com.component.bean.interfaces.node01.beandefinitionregistrypostprocessor.d1;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author shenlx
 * @description
 * @date 2025/2/25 16:09
 */

/**
 * OrangeRegisterPostProcessor是一个BeanDefinitionRegistryPostProcessor。
 * 它的主要作用是检查IOC容器中是否已经包含了名为"orange"的bean定义。
 * 如果没有，它会动态创建一个Orange类的bean定义并注册到容器中。
 */
@Component
public class OrangeRegisterPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {
        System.out.println("postProcessBeanDefinitionRegistry in OrangeRegisterPostProcessor started.");

        if (!registry.containsBeanDefinition("orange")) {
            BeanDefinition orangeDefinition = BeanDefinitionBuilder.genericBeanDefinition(Orange.class).getBeanDefinition();
            registry.registerBeanDefinition("orange", orangeDefinition);
            System.out.println("---------orange:"+orangeDefinition.getBeanClassName());
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        System.out.println("postProcessBeanFactory in OrangeRegisterPostProcessor started.");
    }
}
