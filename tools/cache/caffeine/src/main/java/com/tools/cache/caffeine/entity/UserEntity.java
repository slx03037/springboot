package com.tools.cache.caffeine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shenlx
 * @description
 * @date 2025/1/21 11:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private Integer userId;
    private String name;
    private Integer age;
}
