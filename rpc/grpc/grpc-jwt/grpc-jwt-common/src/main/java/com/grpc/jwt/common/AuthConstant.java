package com.grpc.jwt.common;

import io.grpc.Context;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

/**
 * @author shenlx
 * @description
 * @date 2025/3/31 16:43
 */
public class AuthConstant {
    //这个是生成JWT字符串以及进行JWT字符串校验的密钥
    public  static SecretKey JWT_KEY = Keys.hmacShaKeyFor("hello_javaboy_hello_javaboy_hello_javaboy_hello_javaboy_".getBytes());
    //这个是客户端端ID，即客户端发生来的请求携带了JWT字符串，通过JWT字符串确认了用户身份，就存在这个变量中
    public  static Context.Key<String> AUTH_CLIENT_ID = Context.key("clientId");
    //这个携带JWT字符串的请求头的KET
    public  static String AUTH_HEADER = "Authorization";
    //这个是携带JWT字符串的请求头的参数前缀，通过这个可以确认参数的类型，常见取值有Bearer和Basic
    public  static String AUTH_TOKEN_TYPE = "Bearer";
}
