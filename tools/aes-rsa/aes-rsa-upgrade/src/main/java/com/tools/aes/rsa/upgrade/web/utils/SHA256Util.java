package com.tools.aes.rsa.upgrade.web.utils;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author shenlx
 * @description SHA256 哈希工具类
 * @date 2026/6/23 16:30
 */
public class SHA256Util {
    /**
     * 计算 SHA256 哈希值（十六进制）
     */
    public static String hash(String data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));
        return Hex.encodeHexString(hashBytes);
    }

    /**
     * 计算 HMAC-SHA256
     */
    public static String hmac(String data, String key) throws Exception {
        javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
        javax.crypto.spec.SecretKeySpec keySpec = new javax.crypto.spec.SecretKeySpec(
                key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        mac.init(keySpec);
        byte[] hmacBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Hex.encodeHexString(hmacBytes);
    }
}
