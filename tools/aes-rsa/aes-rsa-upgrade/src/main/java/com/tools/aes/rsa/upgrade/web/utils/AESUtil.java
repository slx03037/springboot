package com.tools.aes.rsa.upgrade.web.utils;

/**
 * @author shenlx
 * @description AES 加密工具类
 * 使用 AES-256-GCM 模式，提供认证加密
 * @date 2026/6/23 16:25
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

@Slf4j
public class AESUtil {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int KEY_SIZE = 256;
    private static final int GCM_IV_LENGTH = 12;   // 96 bits
    private static final int GCM_TAG_LENGTH = 128;   // 128 bits

    /**
     * 生成随机 AES 密钥
     */
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(KEY_SIZE, new SecureRandom());
        return keyGen.generateKey();
    }

    /**
     * 从字节数组创建 SecretKey
     */
    public static SecretKey getKeyFromBytes(byte[] keyBytes) {
        return new SecretKeySpec(keyBytes, 0, keyBytes.length, ALGORITHM);
    }

    /**
     * AES-GCM 加密
     *
     *   @param   data 原始数据
     *   @param   key AES 密钥
     *   @return 加密结果（包含 IV 和密文）
     */
    public static byte[] encrypt(byte[] data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);

        // 生成随机 IV
        byte[] iv = new byte[GCM_IV_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);

        byte[] cipherText = cipher.doFinal(data);

        // 将 IV 和密文拼接在一起（IV 在前 12 字节）
        byte[] result = new byte[iv.length + cipherText.length];
        System.arraycopy(iv, 0, result, 0, iv.length);
        System.arraycopy(cipherText, 0, result, iv.length, cipherText.length);

        return result;
    }

    /**
     * AES-GCM 解密
     *
     *   @param   encryptedData 加密数据（包含 IV）
     *   @param   key AES 密钥
     *   @return 解密后的原始数据
     */
    public static byte[] decrypt(byte[] encryptedData, SecretKey key) throws Exception {
        // 提取 IV（前 12 字节）
        byte[] iv = new byte[GCM_IV_LENGTH];
        System.arraycopy(encryptedData, 0, iv, 0, iv.length);

        // 提取密文
        byte[] cipherText = new byte[encryptedData.length - GCM_IV_LENGTH];
        System.arraycopy(encryptedData, GCM_IV_LENGTH, cipherText, 0, cipherText.length);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);

        return cipher.doFinal(cipherText);
    }

    /**
     * 加密并 Base64 编码
     */
    public static String encryptAndBase64(String data, SecretKey key) throws Exception {
        byte[] encrypted = encrypt(data.getBytes(StandardCharsets.UTF_8), key);
        return Base64.encodeBase64String(encrypted);
    }

    /**
     * Base64 解码并解密
     */
    public static String decryptFromBase64(String base64Data, SecretKey key) throws Exception {
        byte[] encrypted = Base64.decodeBase64(base64Data);
        byte[] decrypted = decrypt(encrypted, key);
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    /**
     * 将密钥转换为 Base64 字符串（用于传输）
     */
    public static String keyToBase64(SecretKey key) {
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * 从 Base64 字符串恢复密钥
     */
    public static SecretKey keyFromBase64(String base64Key) {
        byte[] keyBytes = Base64.decodeBase64(base64Key);
        return getKeyFromBytes(keyBytes);
    }
}
