package com.component.bean.interfaces.node01.applicationcontextawareprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.util.StringValueResolver;

/**
 * @author shenlx
 * @description
 * @date 2025/3/5 15:33
 */
@Slf4j
@Configuration
public class ExtendInvokeAware implements EnvironmentAware, EmbeddedValueResolverAware, ResourceLoaderAware,
        ApplicationEventPublisherAware, MessageSourceAware, ApplicationStartupAware, ApplicationContextAware, BeanNameAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("setApplicationContext--Extend--run {}",applicationContext);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        log.info("setApplicationEventPublisher--Extend--run {}",applicationEventPublisher);
    }

    @Override
    public void setApplicationStartup(ApplicationStartup applicationStartup) {
        log.info("setApplicationStartup--Extend--run {}",applicationStartup);
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        log.info("setEmbeddedValueResolver--Extend--run {}",resolver);
    }

    @Override
    public void setEnvironment(Environment environment) {
        log.info("setEnvironment--Extend--run {}",environment);
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        log.info("setMessageSource--Extend--run {}",messageSource);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        log.info("setResourceLoader--Extend--run {}",resourceLoader);
    }

    @Override
    public void setBeanName(String name) {
        log.info("setBeanName--Extend--run {}",name);
    }
}

