package com.tools.aes.rsa.upgrade.web.model;

/**
 * @author shenlx
 * @description API 请求模型
 * @date 2026/6/23 16:38
 */

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ApiRequest {

    /**
     * 客户端标识
     */
    @NotBlank(message = "clientId 不能为空")
    private String clientId;

    /**
     * 加密后的业务数据（Base64）
     */
    @NotBlank(message = "encryptedData 不能为空")
    private String encryptedData;

    /**
     * 时间戳（秒）
     */
    @NotNull(message = "timestamp 不能为空")
    private Long timestamp;

    /**
     * 随机数（防重放）
     */
    @NotBlank(message = "nonce 不能为空")
    private String nonce;

    /**
     * 数字签名（Base64）
     */
    @NotBlank(message = "signature 不能为空")
    private String signature;

    /**
     * 加密的 AES 密钥（仅在密钥交换时使用）
     */
    private String encryptedAesKey;
}