package com.socket.websocket.webmvc.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author shenlx
 * @description
 * @date 2025/2/27 16:56
 */
@EnableScheduling
@SpringBootApplication
public class Application {
    public static void main(String[]args) throws Exception {

        SpringApplication.run(Application.class,args);
    }
}

