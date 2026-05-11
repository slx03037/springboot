package com.socket.websocket.webfulx.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shenlx
 * @description websocket映射路由注解定义
 * @date 2025/3/2 0:14
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WebSocketMapping {

    /**
     * websocket连接路由地址
     **/
    String value() default "";
}