package com.tools.jwt.basic.entity;

import lombok.Data;

/**
 * @author shenlx
 * @description
 * @date 2026/4/23 9:31
 */
@Data
public class UserInfoDto {
    private Long id;

    private String username;

    private String nickName;
}
