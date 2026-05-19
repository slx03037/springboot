package com.component.rest.basic.web.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author shenlx
 * @description
 * @date 2026/5/19 10:58
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 8784786156844071454L;
    private String name;
    private Integer age;
    private byte sex;

}