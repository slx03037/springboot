package com.tools.mybatisplus.basic.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shenlx
 * @description
 * @date 2026/6/20 21:26
 */
@SpringBootApplication
@MapperScan("com.tools.mybatisplus.basic.web.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
