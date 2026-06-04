package com.component.admin.security.server.web.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author shenlx
 * @description
 * @date 2026/6/3 17:26
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig  {


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
//                .and()
//                .httpBasic()
//                .and()
//                .csrf()
//                .csrfTokenRepository(csrfTokenRepository())
//                .ignoringAntMatchers("/instances", "/actuator/**");
//    }



//    private CsrfTokenRepository csrfTokenRepository() {
//        HttpSessionCsrfTokenRepository repo = new HttpSessionCsrfTokenRepository();
//        repo.setSessionAttributeName("_csrf");
//        return repo;
//    }

    private final AdminServerProperties adminServer;

    public SecurityConfig(AdminServerProperties adminServer) {
        this.adminServer = adminServer;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler =
                new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(adminServer.path("/"));

        http
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .antMatchers(adminServer.path("/assets/**")).permitAll()
                        .antMatchers(adminServer.path("/login")).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage(adminServer.path("/login"))
                        .successHandler(successHandler)
                )
                .logout(logout -> logout
                        .logoutUrl(adminServer.path("/logout"))
                )
                .httpBasic()
                .and()
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers(
                                new AntPathRequestMatcher(adminServer.path("/instances"),
                                        "POST"),
                                new AntPathRequestMatcher(adminServer.path("/instances/*"),
                                        "DELETE"),
                                new AntPathRequestMatcher(adminServer.path("/actuator/**"))
                        ));
        return http.build();
    }


}
