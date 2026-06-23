package com.tools.aes.rsa.basic.web.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author shenlx
 * @description RSA+AES加密工具类（Spring Boot 4.0适配版）
 * @date 2026/6/23 14:15
 */
public class EncryptUtils {
    // 加密算法配置
    private static final String RSA_ALGORITHM = "RSA";
    private static final String AES_ALGORITHM = "AES/ECB/PKCS7Padding";
    private static final int AES_KEY_SIZE = 256;
    private static final int RSA_KEY_SIZE = 2048;
    private static final String SIGN_ALGORITHM = "MD5withRSA";

    static {
        // 加载加密提供者
        Security.addProvider(new BouncyCastleProvider());
    }

    // 1. RSA密钥对生成（公钥+私钥）
    public static KeyPair generateRsaKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        keyPairGenerator.initialize(RSA_KEY_SIZE);
        return keyPairGenerator.generateKeyPair();
    }

    // 2. RSA公钥加密
    public static String rsaEncrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes("UTF-8"));
        return Base64Utils.encodeToString(encryptedData);
    }

    // 3. RSA私钥解密
    public static String rsaDecrypt(String encryptedData, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedData = cipher.doFinal(Base64Utils.decodeFromString(encryptedData));
        return new String(decryptedData, "UTF-8");
    }

    // 4. 生成AES临时密钥
    public static String generateAesKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES_ALGORITHM.split("/")[0]);
        keyGenerator.init(AES_KEY_SIZE);
        SecretKey secretKey = keyGenerator.generateKey();
        return Base64Utils.encodeToString(secretKey.getEncoded());
    }

    // 5. AES加密
    public static String aesEncrypt(String data, String aesKey) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(Base64Utils.decodeFromString(aesKey), AES_ALGORITHM.split("/")[0]);
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedData = cipher.doFinal(data.getBytes("UTF-8"));
        return Base64Utils.encodeToString(encryptedData);
    }

    // 6. AES解密
    public static String aesDecrypt(String encryptedData, String aesKey) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(Base64Utils.decodeFromString(aesKey), AES_ALGORITHM.split("/")[0]);
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decryptedData = cipher.doFinal(Base64Utils.decodeFromString(encryptedData));
        return new String(decryptedData, "UTF-8");
    }

    // 7. RSA签名（私钥签名）
    public static String rsaSign(String data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(data.getBytes("UTF-8"));
        byte[] signBytes = signature.sign();
        return Base64Utils.encodeToString(signBytes);
    }

    // 8. RSA签名验证（公钥验证）
    public static boolean rsaVerify(String data, String sign, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(data.getBytes("UTF-8"));
        return signature.verify(Base64Utils.decodeFromString(sign));
    }

    // 公钥字符串转PublicKey
    public static PublicKey stringToPublicKey(String publicKeyStr) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64Utils.decodeFromString(publicKeyStr));
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }

    // 私钥字符串转PrivateKey
    public static PrivateKey stringToPrivateKey(String privateKeyStr) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64Utils.decodeFromString(privateKeyStr));
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        return keyFactory.generatePrivate(keySpec);
    }
}
