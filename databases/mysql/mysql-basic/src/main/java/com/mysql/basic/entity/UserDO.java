package com.mysql.basic.entity;

import lombok.Data;

/**
 * @author shenlx
 * @description
 * @date 2026/3/6 13:46
 */
@Data
public class UserDO {
    private String userId;

    private String username;

    private String password;

    private String nickName;

    private String sex;

    private String phone;
}
