package com.tools.jwt.basic.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shenlx
 * @description
 * @date 2026/4/14 16:45
 */
@Slf4j
@Component
public class JwtTokenUtil {
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    /**
     * 密钥
     */
    @Value("${jwt.secret}")
    private String secret;
    /**
     * token有效期(S)
     */
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 根据用户信息生成token
     */
    private String generateToken(Map<String, Object> claims) {
        SecretKey key = generateKeyByDecoders();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())  // 签发时间
                .setExpiration(generateExpirationDate()) // 【修复】过期时间（必加）
                .signWith(key)  // 【修复】新版 jjwt 标准写法
                .compact();
    }

    private Date generateExpirationDate() {
        // 秒 → 毫秒
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }


    @SneakyThrows
    public String generateToken(Object userInfo) throws JsonProcessingException {
        Map<String, Object> claims = new HashMap<>();
        Object put = claims.put(CLAIM_KEY_USERNAME, new ObjectMapper().writeValueAsString(userInfo));
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }



    /**
     * 从token中获取用户信息
     */
    @SneakyThrows
    public <T>  T getUserInfoFromToken(String token,Class<T> valueType) throws JsonProcessingException {
        Claims claims = getClaimsFromToken(token);
        return new ObjectMapper().readValue(claims.getSubject(), valueType);
    }



    /**
     * 从token中获取
     */
    private Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(generateKeyByDecoders())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

//    private Claims getClaimsFromToken(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(generateKeyByDecoders())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }




    /**
     * 生成自定义Key
     */
    private SecretKey generateKeyByDecoders() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    /**
     * 刷新token
     */
    public String refreshToken(String token) throws JsonProcessingException {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }


    /**
     * 判断token是否有效
     *
     * @return true 有效
     */
    public boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }


}
