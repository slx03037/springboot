package com.tools.sign.basic.web.controller;

/**
 * @author shenlx
 * @description
 * @date 2026/5/9 15:25
 */

import com.tools.sign.basic.web.common.Result;
import com.tools.sign.basic.web.common.annotation.NoSign;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController {

    /**
     * GET请求 需签名校验接口
     */
    @GetMapping("/get/data")
    public Result<Map<String, Object>> getData(@RequestParam Map<String, Object> params) {
        return Result.success(params);
    }

    /**
     * POST JSON请求体 需签名校验接口
     */
    @PostMapping("/post/json")
    public Result<Object> postJson(@RequestBody Object body) {
        return Result.success(body);
    }

    /**
     * 公开接口 免签名校验
     */
    @NoSign
    @GetMapping("/public/info")
    public Result<String> publicApi() {
        return Result.success("公开接口，无需签名验证，可直接访问");
    }
}
