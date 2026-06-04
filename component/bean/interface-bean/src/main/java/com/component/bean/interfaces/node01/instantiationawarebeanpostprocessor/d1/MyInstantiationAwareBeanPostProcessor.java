package com.component.bean.interfaces.node01.instantiationawarebeanpostprocessor.d1;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author shenlx
 * @description
 * @date 2025/2/26 14:39
 */
@Component
@Slf4j
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @SneakyThrows
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanName.equals("exampleController")) {
            log.info("----postProcessBeforeInstantiation被执行：" + beanName);
            return null;
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (beanName.equals("exampleController")) {
            log.info("----postProcessAfterInstantiation被执行：" + beanName);
        }
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (beanName.equals("exampleController")) {
            log.info("----postProcessProperties被执行：" + beanName);
            MutablePropertyValues mutablePropertyValues=new MutablePropertyValues();
            mutablePropertyValues.addPropertyValue("creator","fanfu");
            pvs=mutablePropertyValues;
        }
        return pvs;
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("exampleController")) {
            log.info("----postProcessBeforeInitialization---" + beanName);
            //如果特定的bean实例化完成后,还未执行InitializingBean.afterPropertiesSet()方法之前,有一些其他操作,可以在这里实现
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("exampleController")) {
            log.info("----postProcessAfterInitialization---" + beanName);
            //如果特定的bean实例化完成,InitializingBean.afterPropertiesSet()方法执行后,有一些其他操作,可以在这里实现
        }
        return bean;
    }
}
