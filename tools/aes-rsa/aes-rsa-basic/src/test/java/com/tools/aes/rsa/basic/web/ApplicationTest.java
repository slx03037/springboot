package com.tools.aes.rsa.basic.web;

import com.alibaba.fastjson.JSON;
import com.tools.aes.rsa.basic.web.utils.EncryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shenlx
 * @description
 * @date 2026/6/23 14:19
 */
@SpringBootTest
@Slf4j
public class ApplicationTest {

    private static final String SERVER_URL = "http://localhost:8080/api";
    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) throws Exception {
        // 1. 第一步：获取服务端RSA公钥
        String publicKeyUrl = SERVER_URL + "/rsa/publicKey";
        Map<String, String> publicKeyResp = restTemplate.getForObject(publicKeyUrl, Map.class);
        String publicKeyStr = publicKeyResp.get("publicKey");
        PublicKey publicKey = EncryptUtils.stringToPublicKey(publicKeyStr);

        // 2. 第二步：生成AES临时密钥
        String aesKey = EncryptUtils.generateAesKey();

        // 3. 第三步：准备请求数据，并AES加密
        Map<String, String> requestData = new HashMap<>();
        requestData.put("username", "admin");
        requestData.put("password", "12345678"); // 敏感数据，加密传输
        String requestDataStr = JSON.toJSONString(requestData);
        String encryptedData = EncryptUtils.aesEncrypt(requestDataStr, aesKey);

        // 4. 第四步：用RSA公钥加密AES密钥
        String encryptedAesKey = EncryptUtils.rsaEncrypt(aesKey, publicKey);

        // 5. 第五步：生成签名（防止数据被篡改）
        String sign = EncryptUtils.rsaSign(encryptedData, EncryptUtils.stringToPrivateKey("客户端私钥，实际场景客户端也需生成RSA密钥对"));

        // 6. 第六步：发送加密请求
        String testUrl = SERVER_URL + "/test/encrypt";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        Map<String, String> params = new HashMap<>();
        params.put("encryptedData", encryptedData);
        params.put("encryptedAesKey", encryptedAesKey);
        params.put("sign", sign);
        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(params, headers);
        Map<String, String> response = restTemplate.postForObject(testUrl, httpEntity, Map.class);

        // 7. 第七步：验证响应签名，并解密响应数据
        String encryptedResponse = response.get("encryptedResponse");
        String responseSign = response.get("sign");
        boolean verify = EncryptUtils.rsaVerify(encryptedResponse, responseSign, publicKey);
        if (verify) {
            String decryptedResponse = EncryptUtils.aesDecrypt(encryptedResponse, aesKey);
            System.out.println("响应解密结果：" + decryptedResponse);
        } else {
            System.out.println("响应签名验证失败，数据可能被篡改");
        }
    }
}
