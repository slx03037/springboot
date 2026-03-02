package com.jdbc.basic.config;

import com.jdbc.basic.entity.Category;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.event.BeforeSaveCallback;
import org.springframework.data.relational.core.mapping.event.RelationalEvent;

/**
 * @author shenlx
 * @description
 * @date 2026/2/24 21:26
 */
@Configuration
public class CategoryConfiguration {
    @Bean
    public ApplicationListener<?> loggingListener() {

        return (ApplicationListener<ApplicationEvent>) event -> {
            if (event instanceof RelationalEvent) {
                System.out.println("Received an event: " + event);
            }
        };
    }

    /**
     * @return {@link BeforeSaveCallback} for {@link Category}.
     */
    @Bean
    public BeforeSaveCallback<Category> timeStampingSaveTime() {

        return (entity, aggregateChange) -> {

            entity.timeStamp();

            return entity;
        };
    }
}
