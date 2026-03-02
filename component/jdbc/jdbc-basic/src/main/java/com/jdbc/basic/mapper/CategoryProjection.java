package com.jdbc.basic.mapper;

/**
 * @author shenlx
 * @description
 * @date 2026/2/24 21:26
 */
public interface CategoryProjection {
    String getName();

    String getDescription();

    default String getNameAndDescription() {
        return getName() + " - " + getDescription();
    }
}
