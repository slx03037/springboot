package com.component.bean.interfaces.node01.applicationcontextinitializer.applicationcontextaware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author shenlx
 * @description
 * @date 2025/3/4 15:06
 */
public class MyBean implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        doSomething();
    }

    public void doSomething() {
        // 使用ApplicationContext对象进行操作
        // 例如获取其他Bean对象
        Cat bean = applicationContext.getBean(Cat.class);
        bean.init2();
    }
}

