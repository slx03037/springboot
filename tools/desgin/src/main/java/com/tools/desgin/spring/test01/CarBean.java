package com.tools.desgin.spring.test01;

import java.util.StringJoiner;

/**
 * @author shenlx
 * @description
 * @date 2024/8/15 17:38
 */
public class CarBean {
    private String name ;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CarBean.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .toString();
    }
}
