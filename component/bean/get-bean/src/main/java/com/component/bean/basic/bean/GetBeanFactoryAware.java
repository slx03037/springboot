package com.component.bean.basic.bean;


import com.component.bean.basic.service.IGetBeanService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * @author shenlx
 * @description
 * @date 2025/3/3 17:11
 */
@Component
public class GetBeanFactoryAware implements BeanFactoryAware, IGetBeanService {

    private BeanFactory beanFactory;
    @Override
    public <T> T get(Class<T> tClass) {
        return beanFactory.getBean(tClass);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory=beanFactory;
    }
}
