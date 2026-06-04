package com.component.bean.interfaces.node01.beanfactorypostprocessor;

import com.component.bean.interfaces.node01.beandefinitionregistrypostprocessor.d1.Fruit;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author shenlx
 * @description
 * @date 2025/2/25 16:15
 */
/**
 * FruitTypeSetterPostProcessor是一个BeanFactoryPostProcessor。
 * 它的主要作用是为所有Fruit类型的bean（Apple和Orange）设置"type"属性。
 * 其中，属性的值与bean的名称相同。
 */
@Component
public class FruitTypeSetterPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        System.out.println("postProcessBeanFactory in FruitTypeSetterPostProcessor started.");

        String[] fruitNames = beanFactory.getBeanNamesForType(Fruit.class);
        for (String name : fruitNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
            beanDefinition.getPropertyValues().add("type", name);
            System.out.println("FruitTypeSetterPostProcessor--------:"+beanDefinition.getBeanClassName());
        }
    }
}
