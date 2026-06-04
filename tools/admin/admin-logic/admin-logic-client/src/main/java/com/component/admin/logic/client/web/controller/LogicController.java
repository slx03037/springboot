package com.component.admin.logic.client.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenlx
 * @description
 * @date 2026/6/3 15:31
 */
@RestController
@Slf4j
public class LogicController {

    @GetMapping("/logic")
    public String logic(){
        String name = Thread.currentThread().getName();
        log.warn("当前请求数据线程名称:{}",name);
        return "操作成功";
    }
}
