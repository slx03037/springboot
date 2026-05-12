package com.tools.desgin.behavior.chainofresponsibility.node03;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * @author shenlx
 * @description
 * @date 2024/10/12 16:26
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Service
public @interface Duty {

    /**
     * 标记具体业务场景
     * @return
     */
    String type() default "";

    /**
     * 排序，数值越小，排序越前
     * @return
     */
    int order() default  0;
}
