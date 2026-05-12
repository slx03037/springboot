package com.tools.desgin.structure.decorator.node03.annotaion;

import org.junit.Test;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * @author shenlx
 * @description
 * @date 2024/8/30 10:09
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Service
public @interface Decorate {
    /**
     * 具体的业务场景
     */
    String scene();

    /**
     *类型，不同业务场景下，不同的装饰器类型
     * @return
     */
    String type();
}
