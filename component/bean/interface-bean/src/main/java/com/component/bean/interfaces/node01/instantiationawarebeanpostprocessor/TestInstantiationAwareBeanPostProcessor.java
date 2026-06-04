package com.component.bean.interfaces.node01.instantiationawarebeanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

/**
 * @author shenlx
 * @description
 * @date 2025/2/25 17:26
 */
@Component
public class TestInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    /**
     * postProcessBeforeInitialization方法的执行时机是在Spring管理的Bean实例化、属性注入完成后，
     * InitializingBean#afterPropertiesSet方法以及自定义的初始化方法之前；
     * @param beanClass the class of the bean to be instantiated
     * @param beanName the name of the bean
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if(isMatchClass(beanClass)){
            System.out.println("调用 postProcessBeforeInstantiation 方法");
        }
        return null;
    }

    /**
     * postProcessAfterInitialization方法的执行时机是在InitializingBean#afterPropertiesSet方法以及自定义的初始化方法之后
     * @param bean the bean instance created, with properties not having been set yet
     * @param beanName the name of the bean
     * @return
     * @throws BeansException
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if(isMatchClass(bean.getClass())){
            System.out.println("调用 postProcessAfterInstantiation 方法");
        }
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if(isMatchClass(bean.getClass())){
            System.out.println("调用 postProcessProperties 方法");
        }
        return pvs;
    }

    private boolean isMatchClass(Class<?> beanClass){
        return TestUser.class.equals(ClassUtils.getUserClass(beanClass));
    }
}
