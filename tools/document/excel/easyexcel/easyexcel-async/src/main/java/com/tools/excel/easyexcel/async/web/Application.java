package com.tools.excel.easyexcel.async.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 13:20
 */
@SpringBootApplication
@MapperScan("com.tools.excel.easyexcel.async.web.mapper")
public class Application {
    public static void main(String[]args){
        SpringApplication.run(Application.class,args);
    }
}