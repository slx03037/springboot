package com.tools.sign.basic.web.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tools.sign.basic.web.common.Result;
import com.tools.sign.basic.web.common.annotation.NoSign;
import com.tools.sign.basic.web.utils.RedisUtil;
import com.tools.sign.basic.web.utils.SignUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shenlx
 * @description 全局签名校验拦截器
 * @date 2026/5/9 14:57
 */
public class SignInterceptor implements HandlerInterceptor {
    @Value("${api.sign-expire}")
    private long signExpire;

    @Resource
    private
    RedisUtil redisUtil;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter writer = response.getWriter();

        //非Controller接口直接放行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // 标注@NoSign注解 免签名直接放行
        if (handlerMethod.hasMethodAnnotation(NoSign.class)) {
            return true;
        }

        // 1. 从请求头获取四大核心参数
        String clientAppKey = request.getHeader("AppKey");
        String timestamp = request.getHeader("Timestamp");
        String nonce = request.getHeader("Nonce");
        String sign = request.getHeader("Sign");

        // 头部参数非空统一校验
        if (clientAppKey == null || timestamp == null || nonce == null || sign == null) {
            writer.write(Result.unAuth("请求头参数缺失，请携带AppKey、Timestamp、Nonce、Sign").toString());
            writer.flush();
            writer.close();
            return false;
        }
        // 2. 校验AppKey合法性（生产此处替换为数据库查询客户端）
        String localAppKey = "test_app_key_123456";
        String localAppSecret = "test_app_secret_654321_abcdef123456";

        if (!localAppKey.equals(clientAppKey)) {
            writer.write(Result.unAuth("AppKey无效，客户端身份认证失败").toString());
            writer.flush();
            writer.close();
            return false;
        }

        // 3. 时间戳校验 拦截过期请求
        long requestTime = Long.parseLong(timestamp);
        long nowTime = System.currentTimeMillis() / 1000;
        long diff = Math.abs(nowTime - requestTime);
        if (diff > signExpire) {
            writer.write(Result.unAuth("请求已过期，请重新发起请求").toString());
            writer.flush();
            writer.close();
            return false;
        }

        // 4. 唯一随机串防重放校验
        if (redisUtil.isNonceExist(nonce)) {
            writer.write(Result.unAuth("重复请求，接口已拦截，禁止重放调用").toString());
            writer.flush();
            writer.close();
            return false;
        }
        redisUtil.setNonceCache(nonce, signExpire);

        Map<String,Object> paramMap=new HashMap<>();
        String contentType = request.getContentType();
        // GET、POST表单参数提取
        request.getParameterMap().forEach((k,v)->paramMap.put(k,v[0]));
        // 兼容POST JSON请求体参数全部参与签名
        if (MediaType.APPLICATION_JSON_VALUE.equals(contentType)){
            BufferedReader reader=request.getReader();
            StringBuilder sb=new StringBuilder();
            String line;
            while ((line=reader.readLine()) !=null){
                sb.append(line);
            }
            String jsonBody = sb.toString();
            
            if (!jsonBody.isEmpty()){
                Map<String,Object> bodyMap= SignUtil.jsonToMap(jsonBody);
                    paramMap.putAll(bodyMap);
            }
        }

        boolean b = SignUtil.verifySign(paramMap, localAppSecret, sign);

        if (!b){
            writer.write(Result.unAuth("接口签名验证失败，参数可能被篡改或秘钥不匹配").toString());
            writer.flush();
            writer.close();
            return false;
        }

        //全部校验通过，接口放行
        return true;

    }
}