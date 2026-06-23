package com.tools.aes.rsa.basic.web.config;

import com.tools.aes.rsa.basic.web.handler.EncryptInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author shenlx
 * @description
 * @date 2026/6/23 14:17
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private EncryptInterceptor encryptInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册加密拦截器，拦截所有接口（除白名单外）
        registry.addInterceptor(encryptInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/public/**", "/api/rsa/publicKey");
    }
}
