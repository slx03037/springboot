package com.component.admin.security.client.web.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author shenlx
 * @description
 * @date 2026/6/4 14:06
 */
@Configuration(proxyBeanMethods = false)
public class ActuatorSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        //开发所有得请求接口
                        .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                        // 允许所有用户访问健康检查端点
                        //.requestMatchers(EndpointRequest.to("health")).permitAll()
                        // 任何其他Actuator端点都需要认证
                        //.requestMatchers(EndpointRequest.toAnyEndpoint()).authenticated()
                        // 所有其他请求也允许访问（可根据需要修改）
                        .anyRequest().permitAll()
                )
                .httpBasic()
                .and()
                // 对于无状态的API端点，通常不需要CSRF保护
                .csrf().disable();

        return http.build();
    }
}