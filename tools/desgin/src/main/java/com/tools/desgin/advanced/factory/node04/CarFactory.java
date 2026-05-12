package com.tools.desgin.advanced.factory.node04;

/**
 * @author shenlx
 * @description
 * @date 2024/8/15 15:28
 */
public interface CarFactory {
    CarEntity getCar(String type);
}
