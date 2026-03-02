package com.database.elasticsearch.basic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shenlx
 * @version 1.0.0
 * @date 2023-11-08 15:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDO {
    private String id;
    private String name;
    private Integer age;
    private String sex;

}
