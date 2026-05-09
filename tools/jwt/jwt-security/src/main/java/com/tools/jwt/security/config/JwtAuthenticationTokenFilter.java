package com.tools.jwt.security.config;

import com.tools.jwt.security.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shenlx
 * @description
 * @date 2026/4/22 10:45
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request
            , HttpServletResponse response
            , FilterChain filterChain) throws ServletException, IOException {
        //从这里获取request中的token
        String authHeader = request.getHeader(jwtTokenUtil.getHeader());
        log.info("authHeader:{}",authHeader);
        //验证token是否存在
        if ( StringUtils.isNotEmpty(authHeader)){
            //根据token获取用户名
            String username= jwtTokenUtil.getUsernameFromToken(authHeader);
            if (username !=null && SecurityContextHolder.getContext().getAuthentication()==null){
                //通过用户名，获取用户信息
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                //验证jwt是否过期
                if (jwtTokenUtil.validateToken(authHeader,userDetails)){
                    //加载用户，角色，权限信息，Spring security根据这些信息判断接口的访问权限
                    UsernamePasswordAuthenticationToken authenticationToken
                            = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(request,response);
        }else {
            filterChain.doFilter(request,response);
        }

    }
}
