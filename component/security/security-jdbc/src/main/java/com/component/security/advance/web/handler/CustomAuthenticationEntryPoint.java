package com.component.security.advance.web.handler;

import com.alibaba.fastjson.JSON;
import com.common.utils.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shenlx
 * @description 请求中认证失败得
 * @date 2026/6/9 10:56
 */
@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String msg = String.format("请求访问：%s，认证失败，无法访问系统资源", request.getRequestURI());
        int code =401;
        log.error("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        String result = JSON.toJSONString(Result.error(code, msg));
        try {
            response.setStatus(200);
           // response.setContentType("application/json");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(result);
        } catch (IOException e) {
           // e.printStackTrace();
            log.error("认证失败,没有请求的访问认证");
        }
    }
}
