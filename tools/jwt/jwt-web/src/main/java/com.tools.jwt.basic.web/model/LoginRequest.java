package com.tools.jwt.basic.web.model;

import lombok.Data;

/**
 * @author shenlx
 * @description
 * @date 2026/5/11 10:47
 */
@Data
public class LoginRequest {
    private String username;

    private String password;
}
