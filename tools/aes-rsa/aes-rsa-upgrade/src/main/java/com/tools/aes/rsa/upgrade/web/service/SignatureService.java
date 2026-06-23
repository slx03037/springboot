package com.tools.aes.rsa.upgrade.web.service;

/**
 * @author shenlx
 * @description 签名服务
 * 负责生成和验证数字签名
 * @date 2026/6/23 16:35
 */

import com.tools.aes.rsa.upgrade.web.utils.RSAUtil;
import com.tools.aes.rsa.upgrade.web.utils.SHA256Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignatureService {

    private final CryptoService cryptoService;

    @Value("${api.security.app-secret:defaultSecret}")
    private String appSecret;

    /**
     * 生成请求签名
     *
     * @param params    请求参数
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @return Base64 编码的签名
     */
    public String generateSignature(Map<String, String> params, long timestamp, String nonce) throws Exception {
        // 1. 参数排序
        String sortedParams = sortParams(params);

        // 2. 拼接签名字符串：sortedParams&timestamp={}&nonce={}&appSecret={}
        String signContent = String.format("%s&timestamp=%d&nonce=%s&appSecret=%s",
                sortedParams, timestamp, nonce, appSecret);

        log.debug("签名原始内容：{}", signContent);

        // 3. SHA256 哈希
        String hash = SHA256Util.hash(signContent);

        // 4. RSA 签名（使用服务端私钥）
        PrivateKey privateKey = cryptoService.getServerPrivateKey();
        byte[] signature = RSAUtil.sign(hash.getBytes(), privateKey);

        // 5. Base64 编码
        return java.util.Base64.getEncoder().encodeToString(signature);
    }

    /**
     * 验证请求签名
     *
     * @param params            请求参数
     * @param timestamp         时间戳
     * @param nonce             随机数
     * @param receivedSignature 接收到的签名
     * @param clientPublicKey   客户端公钥
     * @return 验签结果
     */
    public boolean verifySignature(Map<String, String> params, long timestamp, String nonce,
                                   String receivedSignature, PublicKey clientPublicKey) throws Exception {
        // 1. 参数排序
        String sortedParams = sortParams(params);

        // 2. 拼接签名字符串
        String signContent = String.format("%s&timestamp=%d&nonce=%s&appSecret=%s",
                sortedParams, timestamp, nonce, appSecret);

        // 3. SHA256 哈希
        String hash = SHA256Util.hash(signContent);

        // 4. RSA 验签
        byte[] signature = java.util.Base64.getDecoder().decode(receivedSignature);
        return RSAUtil.verify(hash.getBytes(), signature, clientPublicKey);
    }

    /**
     * 生成响应签名
     */
    public String generateResponseSignature(String responseData, long timestamp) throws Exception {
        String signContent = String.format("%s&timestamp=%d&appSecret=%s",
                responseData, timestamp, appSecret);
        String hash = SHA256Util.hash(signContent);

        PrivateKey privateKey = cryptoService.getServerPrivateKey();
        byte[] signature = RSAUtil.sign(hash.getBytes(), privateKey);

        return java.util.Base64.getEncoder().encodeToString(signature);
    }

    /**
     * 对参数按 key 排序并拼接
     */
    private String sortParams(Map<String, String> params) {
        return new TreeMap<>(params).entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
    }
}
