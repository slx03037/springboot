package com.tools.jwt.security.model;

import lombok.Data;

/**
 * @author shenlx
 * @description
 * @date 2026/4/22 20:37
 */
@Data
public class LoginUser {
    private String username;

    private String password;
}
