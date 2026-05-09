package com.tools.sign.basic.web.common.annotation;

/**
 * @author shenlx
 * @description  接口免签名校验注解  标注在Controller方法上，直接放行所有请求
 * @date 2026/5/9 14:51
 */

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoSign {
}
