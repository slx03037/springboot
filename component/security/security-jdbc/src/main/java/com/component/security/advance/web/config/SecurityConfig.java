package com.component.security.advance.web.config;

import com.component.security.advance.web.handler.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * @author shenlx
 * @description
 * @date 2026/6/5 11:07
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {




    //密码加密器，必须配置，security5+强制加密
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * jdbc用户
     */
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CorsFilter corsFilter;

    @Autowired
    private CustomAuthenticationEntryPoint authenticationEntryPoint;


    //权限、登录配置后续扩展
    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                .csrf().disable()
                    //允许跨域请求，但是在请求之前先校验账号是否存在
                    .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                    //允许跨域请求，但是在请求之前先校验token
                    //.addFilterBefore(corsFilter, TokenAuthenticationFilter.class)
                    //退出
                    .addFilterBefore(corsFilter, LogoutFilter.class)
                    // 防止iframe 造成跨域
                    .headers().frameOptions().disable()
                    // 不创建会话
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    // 放行OPTIONS请求
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    //不需要认证的接口
                    .antMatchers("/login").permitAll()
                    // 所有请求都需要认证
                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling(exceptions -> exceptions
                            .authenticationEntryPoint(authenticationEntryPoint)   // 未认证 → 401
                           // .accessDeniedHandler(restAccessDeniedHandler())            // 已认证但无权限 → 403
                    );

    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }


//    /**
//     * 未认证的请求方式1
//     * @return
//     */
//    @Bean
//    public AuthenticationEntryPoint authenticationEntryPoint() {
//        return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
//    }
//
//    /**
//     * 未认证的请求方式2
//     * @return
//     */
//    @Bean
//    public AuthenticationEntryPoint restAuthenticationEntryPoint() {
//        return (request, response, authException) -> {
//            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("{\"error\":\"Unauthorized\"}");
//        };
//    }

}
