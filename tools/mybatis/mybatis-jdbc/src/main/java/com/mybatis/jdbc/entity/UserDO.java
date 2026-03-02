package com.mybatis.jdbc.entity;

import lombok.Data;

/**
 * @author shenlx
 * @description
 * @date 2026/2/24 13:06
 */
@Data
public class UserDO {
    private String username;
    private Long userId;
    private String phone;
    private Integer sex;
}
