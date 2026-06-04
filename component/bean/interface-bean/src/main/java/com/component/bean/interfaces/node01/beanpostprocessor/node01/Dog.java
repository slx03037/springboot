package com.component.bean.interfaces.node01.beanpostprocessor.node01;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author shenlx
 * @description 定义一个实体类Dog，并实现InitializingBean接口，并且实现afterPropertiesSet()。
 * 其中afterPropertiesSet()和init()是为了演示BeanPostProcessor接口的实现类的postProcessBeforeInitialization
 * 方法和postProcessAfterInitialization方法的执行时机；
 * @date 2025/3/3 15:04
 */

@Getter
@Setter
@Slf4j
public class Dog implements InitializingBean {
    private String name = "旺财";
    private String color = "黑色";

    public Dog() {
        log.info("---dog的无参构造方法被执行");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("---afterPropertiesSet被执行");
    }

    public void init() {
        log.info("---initMethod被执行");
    }

    public void init2() {
        log.info("---initMethod2被执行");
    }
}
