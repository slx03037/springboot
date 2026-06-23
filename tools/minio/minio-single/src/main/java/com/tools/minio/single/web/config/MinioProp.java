package com.tools.minio.single.web.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author shenlx
 * @description
 * @date 2026/1/31 10:08
 */
@Component
@ConfigurationProperties(prefix = "minio")
@Setter
@Getter
public class MinioProp {
    private String endpoint;

    private String accessKey;

    private String secretKey;

    private String bucketName;

    private boolean secure;

    private String region;
}
