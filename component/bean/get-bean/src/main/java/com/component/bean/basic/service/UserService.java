package com.component.bean.basic.service;

import org.springframework.stereotype.Service;

/**
 * @author shenlx
 * @description
 * @date 2025/3/3 17:16
 */

@Service
public class UserService {

    private String name;

    private String age;

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
