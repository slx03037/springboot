package com.tools.jwt.security.utils;

import com.tools.jwt.security.entity.SysUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shenlx
 * @description
 * @date 2026/4/14 17:55
 */
@Data
@ConfigurationProperties(prefix="jwt")//配置自动加载，prefix是配置的前缀
@Component
@Slf4j
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = -6822239287118208167L;

    private String header;
    private String secret;
    private Long expiration;

    /**
     * 生成token令牌
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims=new HashMap<>(2);
        claims.put("sub",userDetails.getUsername());
        claims.put("created",new Date());
        return  generateToken(claims);
    }


    /**
     * 从claims生成令牌，如果看不懂就看谁在调用它
     * @param claims
     * @return
     */
    private String generateToken(Map<String,Object> claims){
        log.info("字符串长度:{}",secret.length());
        long expirations = 7 * 24 * 60 * 60;
        Date expirationDate = new Date(System.currentTimeMillis() + expirations);
        return Jwts.builder().setClaims(claims)
                .setExpiration(expirationDate)
                //.signWith(SignatureAlgorithm.HS512,secret)
                .signWith(SignatureAlgorithm.HS256, secret.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    /**
     * 从令牌中获取用户名
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token){
        String username;
        try{
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        }catch (Exception e){
            log.error(e.getMessage());
            username=null;
        }
        return username;
    }

    private Claims getClaimsFromToken(String token){
        Claims claims;
        try{
            claims = Jwts.parserBuilder().
                    setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token).getBody();

        }catch (Exception e){
            log.error(e.getMessage());
            claims=null;
        }
        return claims;
    }


    /**
     * 判断令牌是否过期
     * @param token
     * @return
     */
    public Boolean isTokenExpired(String token){
        try{
            Claims claims = getClaimsFromToken(token);
            Date expirationDate = claims.getExpiration();
            return expirationDate.before(new Date());
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 刷新令牌
     * @param token
     * @return
     */
    public String refreshToken(String token){
        String refreshToken;
        try{
            Claims claims = getClaimsFromToken(token);
            claims.put("created",new Date());
            refreshToken = generateToken(claims);
        }catch (Exception e){
            log.error(e.getMessage());
            refreshToken=null;
        }
        return refreshToken;
    }

    /**
     * 验证令牌
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token,UserDetails userDetails){
        SysUser user = (SysUser) userDetails;
        String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }
}
