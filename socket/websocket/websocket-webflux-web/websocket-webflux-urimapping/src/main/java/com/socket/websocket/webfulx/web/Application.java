package com.socket.websocket.webfulx.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @author shenlx
 * @description
 * @date 2025/3/2 0:13
 */
@SpringBootApplication
@EnableWebFlux
public class Application {
    public static void main(String[]args){
        SpringApplication.run(Application.class,args);
    }
}
