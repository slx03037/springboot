package com.component.security.advance.web.handler;

import com.component.security.advance.web.cache.UserCache;
import com.component.security.advance.web.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
 * @description 过滤请求，除了放开请求
 * @date 2026/6/6 21:45
 */
@Component
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头中得
        String token = request.getHeader("Authorization");
        log.info("获取到的token为:{}",token);
        if (StringUtils.isNotEmpty(token) && token.startsWith("Bearer ")) {
            token = token.replace("Bearer ", "");
        }

        //todo 后续根据token作为用户标签，查询请求用户信息和权限(token可以使用JWT)
        //todo token刷新token(验证令牌有效期， 相差不足20分钟，自动刷新 token续期)
        //todo 将信息赛如(从jwt中获取出)


        //此方法为本次测试，过期时间和token刷新得操作都没有，后续可以添加redis进行过期时间认证
        UserDTO userDTO = UserCache.getUser(token);
        if (userDTO !=null){
            log.info("用户信息为:{}",userDTO);
            //塞入用户认证信息
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDTO, null, userDTO.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request,response);
    }
}
