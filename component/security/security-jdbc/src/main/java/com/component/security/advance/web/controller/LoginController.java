package com.component.security.advance.web.controller;

import com.common.utils.model.Result;
import com.component.security.advance.web.bo.UserBO;
import com.component.security.advance.web.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenlx
 * @description
 * @date 2026/6/5 10:30
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result<?> login(@RequestBody UserBO userBO){
        return loginService.login(userBO);
    }
}
