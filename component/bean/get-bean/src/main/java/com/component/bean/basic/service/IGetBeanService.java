package com.component.bean.basic.service;

/**
 * @author shenlx
 * @description 容器接口定义
 * @date 2025/3/3 17:10
 */
public interface IGetBeanService {
    <T> T get(Class<T> tClass);
}
