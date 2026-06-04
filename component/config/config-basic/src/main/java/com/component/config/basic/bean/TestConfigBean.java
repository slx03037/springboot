package com.component.config.basic.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @program: my-spring-all-com.xinwen.mybatis.node01.service
 * @description:
 * @author: shenlx
 * @create: 2023-03-10 12:54
 **/
@ConfigurationProperties(prefix = "com")
@Component
@PropertySource("classpath:test.properties")
@Data
@ToString
public class TestConfigBean {
        private String name;

        private int age;
}
