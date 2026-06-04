package com.component.aop.basic.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: my-spring-all-com.xinwen.mybatis.node01.service
 * @description:
 * @author: shenlx
 * @create: 2023-03-12 20:11
 **/
@Data
public class SysLog implements Serializable {
    private Integer id;
    private String username;
    private String operation;
    private Integer time;
    private String method;
    private String params;
    private String ip;
    private Date createTime;
}
