package com.netty.upgrade.web.node02.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author shenlx
 * @description
 * @date 2024/11/11 14:51
 */
@Setter
@Getter
@ToString
@Component
@Configuration
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "socket")
public class SocketProperties {
    private Integer port;
    private String host;
}
