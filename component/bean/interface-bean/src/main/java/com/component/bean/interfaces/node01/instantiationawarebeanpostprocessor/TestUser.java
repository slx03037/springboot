package com.component.bean.interfaces.node01.instantiationawarebeanpostprocessor;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author shenlx
 * @description
 * @date 2025/2/25 17:28
 */
@Component
public class TestUser implements InitializingBean {
    String name;
    String password;

    public TestUser() {
        System.out.println("创建【TestUser】对象");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("设置【name】属性");
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        System.out.println("设置【password】属性");
        this.password = password;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("所有属性设置完毕");
    }
}
