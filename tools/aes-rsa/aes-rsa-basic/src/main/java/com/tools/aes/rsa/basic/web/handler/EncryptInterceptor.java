package com.tools.aes.rsa.basic.web.handler;

import com.alibaba.fastjson.JSON;
import com.tools.aes.rsa.basic.web.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

/**
 * @author shenlx
 * @description  API全链路加密拦截器
 * @date 2026/6/23 14:16
 */

@Component
public class EncryptInterceptor implements HandlerInterceptor {

    @Autowired
    private PublicKey rsaPublicKey;

    @Autowired
    private PrivateKey rsaPrivateKey;

    @Value("${encrypt.exclude-paths}")
    private String[] excludePaths;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    // 预处理：请求解密
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 白名单接口，不加密
        if (isExcludePath(request.getRequestURI())) {
            return true;
        }

        // 1. 获取请求参数（encryptedData：AES加密后的请求数据，encryptedAesKey：RSA加密后的AES密钥，sign：签名）
        String encryptedData = request.getParameter("encryptedData");
        String encryptedAesKey = request.getParameter("encryptedAesKey");
        String sign = request.getParameter("sign");

        // 2. 验证签名（防止数据被篡改）
        boolean verify = EncryptUtils.rsaVerify(encryptedData, sign, rsaPublicKey);
        if (!verify) {
            response.getWriter().write(JSON.toJSONString(Map.of("code", 403, "msg", "签名验证失败，数据可能被篡改")));
            return false;
        }

        // 3. 解密AES密钥（RSA私钥解密）
        String aesKey = EncryptUtils.rsaDecrypt(encryptedAesKey, rsaPrivateKey);

        // 4. 解密请求数据（AES解密）
        String decryptedData = EncryptUtils.aesDecrypt(encryptedData, aesKey);

        // 5. 将解密后的数据重新设置到请求中，供接口使用
        request.setAttribute("decryptedData", decryptedData);
        return true;
    }

    // 后处理：响应加密
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 白名单接口，不加密
        if (isExcludePath(request.getRequestURI())) {
            return;
        }

        // 1. 获取接口响应数据（这里简化处理，实际可通过ResponseWrapper获取）
        String responseData = JSON.toJSONString(Map.of("code", 200, "msg", "success", "data", request.getAttribute("decryptedData")));

        // 2. 获取请求中的AES密钥（实际可存在ThreadLocal中，避免多次解密）
        String encryptedAesKey = request.getParameter("encryptedAesKey");
        String aesKey = EncryptUtils.rsaDecrypt(encryptedAesKey, rsaPrivateKey);

        // 3. AES加密响应数据
        String encryptedResponse = EncryptUtils.aesEncrypt(responseData, aesKey);

        // 4. RSA签名响应数据
        String sign = EncryptUtils.rsaSign(encryptedResponse, rsaPrivateKey);

        // 5. 响应给客户端（密文+签名）
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(Map.of("encryptedResponse", encryptedResponse, "sign", sign)));
    }

    // 判断是否为白名单接口
    private boolean isExcludePath(String requestUri) {
        for (String excludePath : excludePaths) {
            if (antPathMatcher.match(excludePath, requestUri)) {
                return true;
            }
        }
        return false;
    }
}
