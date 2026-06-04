package com.component.bean.interfaces.node01.beanpostprocessor.node01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author shenlx
 * @description 定义MyBeanPostProcessor，并且实现BeanPostProcessor接口；
 * @date 2025/3/3 15:06
 */

@Component
@Slf4j
public class MyBeanPostProcessor implements BeanPostProcessor {

    //AbstractApplicationContext
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("dog")) {
            log.info("postProcessBeforeInitialization---" + beanName);
            //如果特定的bean实例化完成后,还未执行InitializingBean.afterPropertiesSet()方法之前,有一些其他操作,可以在这里实现
            Dog bean1 = (Dog) bean;
            log.info(bean1.getName());
            bean1.setName("福满多");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("dog")) {
            log.info("postProcessAfterInitialization---" + beanName);
            //如果特定的bean实例化完成,InitializingBean.afterPropertiesSet()方法执行后,有一些其他操作,可以在这里实现
        }
        return bean;
    }
}
