package com.component.rest.basic.web.config.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author shenlx
 * @description
 * @date 2026/5/19 11:00
 */
@ConfigurationProperties(prefix = "system.code")
@Component
@Data
public class RestProperty {
    private String authCenter;
    private String  biPlatform;
}
