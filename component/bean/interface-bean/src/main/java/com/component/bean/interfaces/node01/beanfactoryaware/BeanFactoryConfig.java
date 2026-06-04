package com.component.bean.interfaces.node01.beanfactoryaware;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shenlx
 * @description
 * @date 2025/3/25 16:35
 */
@Configuration
public class BeanFactoryConfig {
    @Bean(name = "myCustomBeanName")
    public MyBeanName getMyBeanName() {
        return new MyBeanName();
    }

    @Bean
    public ExtendBeanFactoryAware getExtendBeanFactoryAware () {
        return new ExtendBeanFactoryAware();
    }
}