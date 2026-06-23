package com.tools.aes.rsa.basic.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.PublicKey;
import java.util.Map;

/**
 * @author shenlx
 * @description
 * @date 2026/6/23 14:18
 */

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private PublicKey rsaPublicKey;

    // 1. 获取RSA公钥（白名单接口，供客户端获取公钥）
    @GetMapping("/rsa/publicKey")
    public Map<String, String> getPublicKey() {
        String publicKeyStr = Base64Utils.encodeToString(rsaPublicKey.getEncoded());
        return Map.of("publicKey", publicKeyStr, "code", "200", "msg", "success");
    }

    // 2. 测试全链路加密接口（需加密）
    @PostMapping("/test/encrypt")
    public Map<String, Object> testEncrypt(HttpServletRequest request) {
        // 获取拦截器解密后的请求数据
        String decryptedData = (String) request.getAttribute("decryptedData");
        JSONObject jsonObject = JSON.parseObject(decryptedData);
        // 模拟业务处理
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        return Map.of("code", 200, "msg", "加密请求处理成功", "data", Map.of("username", username, "tip", "密码已加密存储"));
    }
}
