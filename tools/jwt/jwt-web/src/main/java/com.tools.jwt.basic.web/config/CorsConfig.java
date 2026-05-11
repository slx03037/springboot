package com.tools.jwt.basic.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("https://example.com", "http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }



    // 或者在 SecurityConfig 中配置
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http
//        .csrf(csrf -> csrf
//                            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//        )
//        .authorizeHttpRequests(auth -> auth
//                            .antMatchers("/api/**").permitAll()
//            .anyRequest().authenticated()
//        );
//
//    return http.build();
//    }

}