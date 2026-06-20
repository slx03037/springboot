package com.tools.mybatisplus.basic.web.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author shenlx
 * @description
 * @date 2026/6/20 20:55
 */
@Data
public class UserQueryBean implements Serializable {
    private static final long serialVersionUID = -2541184105479944358L;

    private String username;

    private String description;

    private long phoneNumber;

    private String email;
}
