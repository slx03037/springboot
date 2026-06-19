package com.tools.sftp.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author shenlx
 * @description
 * @date 2026/6/19 20:46
 */
@Data
@Component
@ConfigurationProperties(prefix = "sftp")
public class SftpProperties {
    private String host;
    private int port = 22;
    private String username;
    private String password;
    private String privateKeyPath;
    private int connectTimeout = 10000;
    private int sessionTimeout = 30000;
    private Pool pool = new Pool();
    private String rootDirectory = "/";

    @Data
    public static class Pool {
        private int maxTotal = 10;
        private int maxIdle = 5;
        private int minIdle = 2;
    }
}