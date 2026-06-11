package com.component.security.advance.web.controller;

import com.common.utils.model.Result;
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
}
