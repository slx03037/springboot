package com.tools.aes.rsa.upgrade.web.filter;

/**
 * @author shenlx
 * @description API 安全过滤器
 * 负责请求的解密、验签、防重放校验
 * @date 2026/6/23 16:37
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tools.aes.rsa.upgrade.web.model.ApiRequest;
import com.tools.aes.rsa.upgrade.web.model.ApiResponse;
import com.tools.aes.rsa.upgrade.web.service.CryptoService;
import com.tools.aes.rsa.upgrade.web.service.ReplayAttackService;
import com.tools.aes.rsa.upgrade.web.service.SignatureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiSecurityFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;
    private final CryptoService cryptoService;
    private final SignatureService signatureService;
    private final ReplayAttackService replayAttackService;

    // 不需要安全校验的路径
    private static final String[] EXCLUDED_PATHS = {
            "/api/public/**",
            "/api/key/exchange",
            "/actuator/**"
    };

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        // 检查是否需要排除
        if (isExcluded(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // 1. 解析请求体
            String requestBody = getRequestBody(request);
            ApiRequest apiRequest = objectMapper.readValue(requestBody, ApiRequest.class);

            // 2. 防重放校验
            if (!replayAttackService.validateRequest(
                    apiRequest.getTimestamp(),
                    apiRequest.getNonce(),
                    apiRequest.getClientId())) {
                throw new SecurityException("请求可能为重放攻击，已拒绝");
            }

            // 3. 获取会话密钥
            SecretKey sessionKey = cryptoService.getSessionKey(apiRequest.getClientId());
            if (sessionKey == null) {
                throw new SecurityException("会话密钥不存在，请先进行密钥交换");
            }

            // 4. 解密业务数据
            String decryptedData = cryptoService.decryptBusinessData(
                    apiRequest.getEncryptedData(), sessionKey);

            // 5. 验签
            Map<String, String> params = objectMapper.readValue(decryptedData, Map.class);
            boolean isValid = signatureService.verifySignature(
                    params,
                    apiRequest.getTimestamp(),
                    apiRequest.getNonce(),
                    apiRequest.getSignature(),
                    null    // 实际场景需要客户端公钥
            );

            if (!isValid) {
                throw new SecurityException("签名验证失败");
            }

            // 6. 将解密后的数据放入请求属性，供后续 Controller 使用
            request.setAttribute("DECRYPTED_DATA", decryptedData);
            request.setAttribute("CLIENT_ID", apiRequest.getClientId());

            log.info("安全校验通过，clientId: {}", apiRequest.getClientId());

            // 7. 继续过滤器链
            filterChain.doFilter(request, response);

        } catch (SecurityException e) {
            log.error("安全校验失败：{}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");

            ApiResponse<?> errorResponse = ApiResponse.error(401, e.getMessage());
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        } catch (Exception e) {
            log.error("请求处理异常", e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json;charset=UTF-8");

            ApiResponse<?> errorResponse = ApiResponse.error(400, "请求处理失败");
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        }
    }

    /**
     * 判断路径是否被排除
     */
    private boolean isExcluded(String requestURI) {
        for (String excludedPath : EXCLUDED_PATHS) {
            if (requestURI.matches(excludedPath.replace("**", ".*"))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取请求体内容
     */
    private String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        try (java.io.BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString();
    }
}
