package com.component.bean.basic.bean;


import com.component.bean.basic.service.IGetBeanService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author shenlx
 * @description
 * @date 2025/3/3 17:13
 */
@Component
public class GetByApplicationContextAware implements ApplicationContextAware, IGetBeanService {

    private ApplicationContext applicationContext;


    @Override
    public <T> T get(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext=applicationContext;
    }
}
