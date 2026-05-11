package com.socket.websocket.webflux.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @author shenlx
 * @description
 * @date 2025/2/28 13:25
 */
@EnableWebFlux
@SpringBootApplication
public class Application {
    public static void main(String[]args)  {
        SpringApplication.run(Application.class,args);
    }
}
