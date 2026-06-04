package com.component.bean.interfaces.node01.beanfactoryaware;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * @author shenlx
 * @description
 * @date 2025/3/25 16:34
 */
@Component
public class MyBeanName implements BeanNameAware {
    @Override
    public void setBeanName(String name) {
        System.out.println(name);
    }

    @Override
    public String toString() {
        return "这是 OtherBean 实例";
    }
}
