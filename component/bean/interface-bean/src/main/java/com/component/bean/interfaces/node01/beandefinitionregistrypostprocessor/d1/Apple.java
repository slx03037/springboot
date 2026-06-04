package com.component.bean.interfaces.node01.beandefinitionregistrypostprocessor.d1;

import org.springframework.stereotype.Component;

/**
 * @author shenlx
 * @description
 * @date 2025/2/25 16:04
 */
@Component
public class Apple extends Fruit{
    @Override
    public String toString() {
        return "Apple{" + "type='" + type + '\'' + "}";
    }
}
