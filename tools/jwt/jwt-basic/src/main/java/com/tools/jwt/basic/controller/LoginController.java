package com.tools.jwt.basic.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tools.jwt.basic.entity.UserInfoDto;
import com.tools.jwt.basic.model.UserInfoVo;
import com.tools.jwt.basic.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shenlx
 * @description
 * @date 2026/4/14 17:33
 */
@RestController
@Slf4j
public class LoginController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    /**
     * 登录
     * -
     * http://127.0.0.1:9090/login
     */
    @PostMapping("login")
    public String login(@RequestBody UserInfoVo userInfoVo) throws JsonProcessingException {

        // 存放用户信息到token
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setId(userInfoVo.getId());
        userInfoDto.setUsername(userInfoVo.getUsername());
        userInfoDto.setNickName(userInfoVo.getNickName());
        // 响应token
        return jwtTokenUtil.generateToken(userInfoDto);
    }

    /**
     * 通过token获取用户信息
     * -
     * http://127.0.0.1:9090/user/info
     */
    @GetMapping("info")
    public UserInfoDto info(HttpServletRequest request) {
        try {
            String token = request.getHeader("token");
            return getUserInfo(token);
        } catch (ExpiredJwtException e) {
            log.error("token已过期", e);

            UserInfoDto userInfoDto = new UserInfoDto();
            userInfoDto.setNickName("token已过期");
            userInfoDto.setUsername("token已过期");
            return userInfoDto;
        } catch (JsonProcessingException e) {

            UserInfoDto userInfoDto = new UserInfoDto();
            userInfoDto.setNickName("token已过期");
            userInfoDto.setUsername("token已过期");
            return userInfoDto;
        }
    }

    public UserInfoDto getUserInfo(String token) throws JsonProcessingException {
        return jwtTokenUtil.getUserInfoFromToken(token, UserInfoDto.class);
    }

}
