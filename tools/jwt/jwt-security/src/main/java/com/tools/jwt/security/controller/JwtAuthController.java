package com.tools.jwt.security.controller;


import com.common.utils.model.Result;
import com.tools.jwt.security.model.LoginUser;
import com.tools.jwt.security.service.JwtAuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author shenlx
 * @description
 * @date 2026/4/14 20:46
 */
@RestController
@Slf4j
public class JwtAuthController {

    @Resource
    private JwtAuthService jwtAuthService;

    @PostMapping(value = "/authentication")
    public Result<?> login(@RequestBody LoginUser loginUser){
        log.info("请求数据为:{}",loginUser);

        String username=loginUser.getUsername();

        String password=loginUser.getPassword();

        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            return Result.error("用户名和密码不可为空");
        }
        return Result.success(jwtAuthService.login(username,password));

    }

    @PostMapping(value = "refreshToekn")
    public Result<?> refresh(@RequestHeader("${jwt.header}")String token){
        return Result.success(jwtAuthService.refreshToken(token));
    }
}
