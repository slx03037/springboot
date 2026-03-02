package com.tools.cache.caffeine.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author shenlx
 * @description
 * @date 2025/1/21 11:11
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CacheEnum {
    CODE_1("TEST_1",2000);
    private String name;

    private int expires;
}
