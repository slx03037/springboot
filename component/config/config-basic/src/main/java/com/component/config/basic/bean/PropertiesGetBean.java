package com.component.config.basic.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: my-spring-all-com.xinwen.mybatis.node01.service
 * @description:
 * @author: shenlx
 * @create: 2023-03-10 11:18
 **/
@Component
@Data
public class PropertiesGetBean {
    @Value("${server.port}")
    private String port;
}
