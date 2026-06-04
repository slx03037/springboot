package com.component.bean.basic.bean;

import com.component.bean.basic.service.IGetBeanService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author shenlx
 * @description
 * @date 2025/3/3 17:14
 */
@Component
public class GetByApplicationListener implements ApplicationListener<ContextRefreshedEvent>, IGetBeanService {

    ApplicationContext applicationContext;

    @Override
    public <T> T get(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
            this.applicationContext=applicationContext;
    }
}
