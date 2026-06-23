package com.tools.aes.rsa.upgrade.web.service;

/**
 * @author shenlx
 * @description 加密服务
 * 负责密钥管理、数据加解密
 * @date 2026/6/23 16:35
 */

import com.tools.aes.rsa.upgrade.web.utils.AESUtil;
import com.tools.aes.rsa.upgrade.web.utils.RSAUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class CryptoService {

    private final StringRedisTemplate redisTemplate;

    // 服务端密钥对（长期保存）
    private final KeyPair serverKeyPair;
    private final String SERVER_PRIVATE_KEY = "server_private_key";
    private final String SERVER_PUBLIC_KEY = "server_public_key";

    /**
     * 初始化服务端密钥对
     */
    public void initServerKeyPair() throws Exception {
        // 检查 Redis 中是否已存在
        String storedPrivateKey = redisTemplate.opsForValue().get(SERVER_PRIVATE_KEY);
        String storedPublicKey = redisTemplate.opsForValue().get(SERVER_PUBLIC_KEY);

        if (storedPrivateKey != null && storedPublicKey != null) {
            log.info("从 Redis 加载已存在的服务器密钥对");
            return;
        }

        // 生成新密钥对
        KeyPair keyPair = RSAUtil.generateKeyPair();
        String privateKeyBase64 = RSAUtil.privateKeyToBase64(keyPair.getPrivate());
        String publicKeyBase64 = RSAUtil.publicKeyToBase64(keyPair.getPublic());

        // 存入 Redis（实际生产环境应使用更安全的存储方式）
        redisTemplate.opsForValue().set(SERVER_PRIVATE_KEY, privateKeyBase64);
        redisTemplate.opsForValue().set(SERVER_PUBLIC_KEY, publicKeyBase64);

        log.info("服务器密钥对初始化完成");
    }

    /**
     * 获取服务端公钥（提供给客户端）
     */
    public String getServerPublicKey() {
        return redisTemplate.opsForValue().get(SERVER_PUBLIC_KEY);
    }

    /**
     * 获取服务端私钥
     */
    public PrivateKey getServerPrivateKey() throws Exception {
        String privateKeyBase64 = redisTemplate.opsForValue().get(SERVER_PRIVATE_KEY);
        return RSAUtil.privateKeyFromBase64(privateKeyBase64);
    }

    /**
     * 解密客户端发送的 AES 密钥
     */
    public SecretKey decryptAesKey(String encryptedAesKeyBase64, String clientId) throws Exception {
        PrivateKey privateKey = getServerPrivateKey();
        String decryptedAesKey = RSAUtil.decryptFromBase64(encryptedAesKeyBase64, privateKey);
        return AESUtil.keyFromBase64(decryptedAesKey);
    }

    /**
     * 使用 AES 密钥解密业务数据
     */
    public String decryptBusinessData(String encryptedData, SecretKey aesKey) throws Exception {
        return AESUtil.decryptFromBase64(encryptedData, aesKey);
    }

    /**
     * 使用 AES 密钥加密业务数据
     */
    public String encryptBusinessData(String data, SecretKey aesKey) throws Exception {
        return AESUtil.encryptAndBase64(data, aesKey);
    }

    /**
     * 缓存会话密钥（带过期时间）
     */
    public void cacheSessionKey(String clientId, SecretKey aesKey) {
        String keyBase64 = AESUtil.keyToBase64(aesKey);
        String redisKey = "api:session:" + clientId;
        // 密钥有效期 24 小时
        redisTemplate.opsForValue().set(redisKey, keyBase64, 24, TimeUnit.HOURS);
        log.info("会话密钥已缓存，clientId: {}", clientId);
    }

    /**
     * 获取缓存的会话密钥
     */
    public SecretKey getSessionKey(String clientId) {
        String redisKey = "api:session:" + clientId;
        String keyBase64 = redisTemplate.opsForValue().get(redisKey);
        if (keyBase64 == null) {
            return null;
        }
        return AESUtil.keyFromBase64(keyBase64);
    }
}
