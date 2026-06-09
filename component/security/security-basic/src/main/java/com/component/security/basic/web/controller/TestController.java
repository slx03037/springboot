package com.component.security.basic.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenlx
 * @description
 * @date 2026/6/5 10:30
 */
@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello(){
        return "登录成功访问资源";
    }
}
