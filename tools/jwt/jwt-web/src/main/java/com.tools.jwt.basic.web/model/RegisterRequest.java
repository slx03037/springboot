package com.tools.jwt.basic.web.model;

import lombok.Data;

/**
 * @author shenlx
 * @description
 * @date 2026/5/11 10:49
 */
@Data
public class RegisterRequest {
    private String username;

    private String email;

    private String password;

    private String phone;

    private Integer status;
}
