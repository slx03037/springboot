package com.tools.sftp.web.config;

import com.jcraft.jsch.ChannelSftp;
import com.tools.sftp.web.factory.SftpFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shenlx
 * @description
 * @date 2026/6/19 20:48
 */
@Configuration
public class SftpPoolConfig {
    @Bean
    public SftpFactory sftpFactory(SftpProperties properties) {
        return new SftpFactory(properties);
    }

    @Bean
    public GenericObjectPool<ChannelSftp> sftpPool(SftpFactory factory, SftpProperties properties) {
        GenericObjectPoolConfig<ChannelSftp> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxTotal(properties.getPool().getMaxTotal());
        poolConfig.setMaxIdle(properties.getPool().getMaxIdle());
        poolConfig.setMinIdle(properties.getPool().getMinIdle());
        return new GenericObjectPool<>(factory, poolConfig);
    }
}
