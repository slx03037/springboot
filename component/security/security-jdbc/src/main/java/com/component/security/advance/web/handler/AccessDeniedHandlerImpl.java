package com.component.security.advance.web.handler;

import com.alibaba.fastjson.JSON;
import com.common.utils.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shenlx
 * @description
 * @date 2026/6/12 21:57
 */
@Component
@Slf4j
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        String json = JSON.toJSONString(Result.error(HttpStatus.FORBIDDEN.value(), "权限不足"));
        //WebUtils.renderString(response,json);
        try{
            response.setStatus(200);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(json);
        }catch (IOException e) {
            // e.printStackTrace();
            log.error("认证失败,没有请求的访问认证");
        }

    }
}
