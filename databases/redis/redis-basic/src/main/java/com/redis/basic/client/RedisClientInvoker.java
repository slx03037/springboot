package com.redis.basic.client;

import redis.clients.jedis.Jedis;

import java.io.IOException;

/**
 * @author shenlx
 * @description
 * @date 2024/1/2 22:26
 */
public interface RedisClientInvoker<T> {
    /**
     * invoke
     *
     * @param jedis
     * @return
     * @throws IOException
     */
    T invoke(Jedis jedis) throws IOException;
}
