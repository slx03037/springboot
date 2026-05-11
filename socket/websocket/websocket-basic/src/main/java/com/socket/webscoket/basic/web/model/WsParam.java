package com.socket.webscoket.basic.web.model;

import lombok.Data;

/**
 * @program: my-spring-all-com.xinwen.mybatis.node01.service
 * @description:
 * @author: shenlx
 * @create: 2023-02-02 20:56
 **/
@Data
public class WsParam<T> {
    private String method;
    private T param;
}
