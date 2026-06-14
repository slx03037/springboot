package com.component.security.advance.web.controller;

import com.common.utils.model.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenlx
 * @description
 * @date 2026/6/10 10:20
 */
@RestController
public class TestController {

    @GetMapping("test")
    public Result<?> testMethod(){
        return Result.success("test，操作成功");
    }

    @GetMapping("authorization")
    @PreAuthorize("hasRole('ADMIN')")

    public Result<?> authorization(){
        return Result.success("test，操作成功");
    }

    @GetMapping("authorization0")
    //@PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')") //ROLE_ADMIN和ADMIN 都可以
    public Result<?> authorization0(){
        return Result.success("test，操作成功");
    }

    @GetMapping("authorization1")
    @PreAuthorize("hasAuthority('system:user:add')")
    public Result<?> authorization1(){
        return Result.success("test，操作成功");
    }

    @GetMapping("authorization2")
    @PreAuthorize("hasAnyAuthority('system:user:list','system:user:add')")
    public Result<?> authorization2(){
        return Result.success("test，操作成功");
    }
}
