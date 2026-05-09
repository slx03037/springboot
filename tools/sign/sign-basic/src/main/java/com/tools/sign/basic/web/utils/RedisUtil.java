package com.tools.sign.basic.web.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author shenlx
 * @description
 * @date 2026/5/9 14:50
 */
@Component
public class RedisUtil {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final String NONCE_PREFIX = "api:sign:nonce:";

    /**
     * 判断随机串是否已经使用过（防重放）
     */
    public boolean isNonceExist(String nonce) {
        return Boolean.TRUE.equals(stringRedisTemplate.hasKey(NONCE_PREFIX + nonce));
    }

    /**
     * 存入随机串 绑定过期时间
     */
    public void setNonceCache(String nonce, long expireSecond) {
        stringRedisTemplate.opsForValue().set(NONCE_PREFIX + nonce, "1", expireSecond, TimeUnit.SECONDS);
    }
}
