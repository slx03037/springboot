package com.tools.aes.rsa.upgrade.web.service;

/**
 * @author shenlx
 * @description 防重放攻击服务
 * 通过时间戳和 Nonce 机制防止请求重放
 * @date 2026/6/23 16:36
 */

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReplayAttackService {

    private final StringRedisTemplate redisTemplate;

    // 允许的最大时间差（秒）
    private static final long MAX_TIME_DIFF = 300;    // 5 分钟

    /**
     * 校验时间戳
     */
    public boolean validateTimestamp(long requestTimestamp) {
        long currentTimestamp = System.currentTimeMillis() / 1000;
        long timeDiff = Math.abs(currentTimestamp - requestTimestamp);

        if (timeDiff > MAX_TIME_DIFF) {
            log.warn("时间戳校验失败，时间差：{}秒，请求时间戳：{}, 当前时间：{}",
                    timeDiff, requestTimestamp, currentTimestamp);
            return false;
        }

        return true;
    }

    /**
     * 校验 Nonce（防止重放）
     *
     * @param nonce    随机数
     * @param clientId 客户端标识
     * @return true=校验通过（首次使用），false=校验失败（已使用过）
     */
    public boolean validateNonce(String nonce, String clientId) {
        String redisKey = "api:nonce:" + clientId + ":" + nonce;

        // 尝试设置 nonce，如果已存在则返回 false
        Boolean isNew = redisTemplate.opsForValue()
                .setIfAbsent(redisKey, "1", MAX_TIME_DIFF, TimeUnit.SECONDS);

        if (Boolean.FALSE.equals(isNew)) {
            log.warn("Nonce 重复使用，可能为重放攻击！clientId: {}, nonce: {}", clientId, nonce);
            return false;
        }

        return true;
    }

    /**
     * 完整的防重放校验
     */
    public boolean validateRequest(long timestamp, String nonce, String clientId) {
        // 1. 校验时间戳
        if (!validateTimestamp(timestamp)) {
            return false;
        }

        // 2. 校验 Nonce
        if (!validateNonce(nonce, clientId)) {
            return false;
        }

        return true;
    }
}