package com.component.bean.interfaces.node01.beanfactoryaware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * @author shenlx
 * @description
 * @date 2025/3/25 16:51
 */
@Slf4j
@Component
public class ExtendBeanFactoryAware implements BeanNameAware, BeanFactoryAware {
    private BeanFactory beanFactory;
    @Override
    public void setBeanName(String name) {
        log.info("ExtendBeanNameAware-2--beanName:{}",name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("ExtendBeanFactoryAware-3--beanName:{}",beanFactory);
    }

    public void getOtherBean() {
        MyBeanName otherBean = beanFactory.getBean(MyBeanName.class);
        System.out.println("获取 otherBean 实例: " + otherBean);
    }
}

