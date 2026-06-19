package com.tools.ftp.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author shenlx
 * @description
 * @date 2026/6/19 20:51
 */
@Data
@Component
@ConfigurationProperties(prefix = "ftp")
public class FtpProperties {
    private String host;
    private int port;
    private String username;
    private String password;
    private String basePath;
}
