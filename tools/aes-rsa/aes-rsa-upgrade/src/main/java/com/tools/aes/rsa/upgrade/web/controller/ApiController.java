package com.tools.aes.rsa.upgrade.web.controller;

/**
 * @author shenlx
 * @description API 控制器
 * @date 2026/6/23 16:39
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tools.aes.rsa.upgrade.web.model.ApiRequest;
import com.tools.aes.rsa.upgrade.web.model.ApiResponse;
import com.tools.aes.rsa.upgrade.web.service.CryptoService;
import com.tools.aes.rsa.upgrade.web.service.SignatureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final ObjectMapper objectMapper;
    private final CryptoService cryptoService;
    private final SignatureService signatureService;

    /**
     * 密钥交换接口
     * 客户端调用此接口获取服务端公钥，并上传加密的 AES 密钥
     */
    @PostMapping("/key/exchange")
    public ApiResponse<Map<String, String>> keyExchange(@RequestBody ApiRequest request) {
        try {
            // 1. 返回服务端公钥
            String serverPublicKey = cryptoService.getServerPublicKey();

            // 2. 解密客户端上传的 AES 密钥
            SecretKey aesKey = cryptoService.decryptAesKey(
                    request.getEncryptedAesKey(), request.getClientId());

            // 3. 缓存会话密钥
            cryptoService.cacheSessionKey(request.getClientId(), aesKey);

            Map<String, String> result = Map.of(
                    "serverPublicKey", serverPublicKey,
                    "status", "success"
            );

            log.info("密钥交换成功，clientId: {}", request.getClientId());
            return ApiResponse.success(result);

        } catch (Exception e) {
            log.error("密钥交换失败", e);
            return ApiResponse.error(500, "密钥交换失败：" + e.getMessage());
        }
    }

    /**
     * 获取服务端公钥
     */
    @GetMapping("/public/key")
    public ApiResponse<String> getPublicKey() {
        String publicKey = cryptoService.getServerPublicKey();
        return ApiResponse.success(publicKey);
    }

    /**
     * 示例业务接口（需要安全校验，由过滤器处理）
     */
    @PostMapping("/order/create")
    public ApiResponse<Map<String, Object>> createOrder(HttpServletRequest request) {
        try {
            // 从请求属性中获取解密后的数据（由过滤器设置）
            String decryptedData = (String) request.getAttribute("DECRYPTED_DATA");
            String clientId = (String) request.getAttribute("CLIENT_ID");

            Map<String, Object> orderData = objectMapper.readValue(decryptedData, Map.class);

            // 业务逻辑处理...
            log.info("创建订单，clientId: {}, 订单数据：{}", clientId, orderData);

            Map<String, Object> result = Map.of(
                    "orderId", "ORD" + System.currentTimeMillis(),
                    "status", "created"
            );

            return ApiResponse.success(result);

        } catch (Exception e) {
            log.error("创建订单失败", e);
            return ApiResponse.error(500, "创建订单失败");
        }
    }
}
