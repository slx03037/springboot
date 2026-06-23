package com.tools.aes.rsa.upgrade.web.utils;

/**
 * @author shenlx
 * @description RSA 加密工具类
 * 用于密钥交换和数字签名
 * @date 2026/6/23 16:26
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


@Slf4j
public class RSAUtil {

    private static final String ALGORITHM = "RSA";
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    private static final int KEY_SIZE = 2048;

    /**
     * 生成 RSA 密钥对
     */
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGen.initialize(KEY_SIZE);
        return keyPairGen.generateKeyPair();
    }

    /**
     * 支持 SecureRandom（增强随机性）
     */
    public static KeyPair generateKeyPair(String algorithm, int keySize, SecureRandom random) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(algorithm);
        keyPairGen.initialize(keySize, random);
        return keyPairGen.generateKeyPair();
    }

    /**
     * RSA 加密（使用公钥）
     */
    public static byte[] encrypt(byte[] data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * RSA 解密（使用私钥）
     */
    public static byte[] decrypt(byte[] encryptedData, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(encryptedData);
    }

    /**
     * RSA 签名
     */
    public static byte[] sign(byte[] data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }

    /**
     * RSA 验签
     */
    public static boolean verify(byte[] data, byte[] signature, PublicKey publicKey) throws Exception {
        Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
        sig.initVerify(publicKey);
        sig.update(data);
        return sig.verify(signature);
    }

    /**
     * 公钥转 Base64
     */
    public static String publicKeyToBase64(PublicKey publicKey) {
        return Base64.encodeBase64String(publicKey.getEncoded());
    }

    /**
     * 私钥转 Base64
     */
    public static String privateKeyToBase64(PrivateKey privateKey) {
        return Base64.encodeBase64String(privateKey.getEncoded());
    }

    /**
     * 从 Base64 恢复公钥
     */
    public static PublicKey publicKeyFromBase64(String base64Key) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(base64Key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 从 Base64 恢复私钥
     */
    public static PrivateKey privateKeyFromBase64(String base64Key) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(base64Key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 加密并 Base64 编码
     */
    public static String encryptAndBase64(String data, PublicKey publicKey) throws Exception {
        byte[] encrypted = encrypt(data.getBytes(StandardCharsets.UTF_8), publicKey);
        return Base64.encodeBase64String(encrypted);
    }

    /**
     * Base64 解码并解密
     */
    public static String decryptFromBase64(String base64Data, PrivateKey privateKey) throws Exception {
        byte[] encrypted = Base64.decodeBase64(base64Data);
        byte[] decrypted = decrypt(encrypted, privateKey);
        return new String(decrypted, StandardCharsets.UTF_8);
    }
}
