package com.tools.sign.basic.web.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author shenlx
 * @description 接口签名工具类 AppKey+Secret 完整版 兼容GET/POST/JSON请求体、参数排序、加密、签名校验
 * @date 2026/5/9 10:51
 */
public class SignUtil {
    private static final ObjectMapper OBJECT_MAPPER =new ObjectMapper();

    /**
     * MD5加密32位大写
     * @param str
     * @return
     */
    public static String md5(String str){
        try{
            MessageDigest md=MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(str.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b:bytes){
                int i=b & 0xff;
                if (i<16){
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString().toUpperCase();
        }catch (Exception e){
            throw new RuntimeException("MD5签名加密异常");
        }
    }


    /**
     * HMAC-SHA256 加密 安全性高于MD5 生成推荐
     * @param data
     * @param secret
     * @return
     */
    public static String hmacSha256(String data,String secret){
        try{
            SecretKeySpec secretKey = new SecretKeySpec(secret
                    .getBytes(StandardCharsets.UTF_8)
                    , "HmacSHA256"
            );

            Mac mac = Mac.getInstance("HmacSHA256");

            mac.init(secretKey);

            byte[] bytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();

            for (byte b: bytes){
                String hex = Integer.toHexString(b & 0xff);

                if (hex.length()==1){
                    sb.append("0");
                }

                sb.append(hex);
            }

            return sb.toString().toUpperCase();
        }catch (Exception e){
            throw new RuntimeException("HMAC-SHA256加密异常");
        }
    }

    /**
     * 通用参数排序拼接，过滤空值参数
     * @param params
     * @return
     */
    public  static String paramsSortJoin(Map<String,Object> params){
        if (params==null || params.isEmpty()){
            return "";
        }

        //字典序升序排序，自动过滤null,空值参数
        TreeMap<String, Object> treeMap = new TreeMap<>();

        params.forEach((k,v)->{
            if (v !=null && !StringUtils.isEmpty(v.toString())){
                treeMap.put(k,v);
            }
        });

        //拼接 key=value & key = value
        return treeMap.entrySet()
                .stream()
                .map(entry-> entry.getKey()+"-"+entry.getValue())
                .collect(Collectors.joining("&"));

    }

    /**
     * 生成接口签名，MD5版本
     * @param params
     * @param appSecret
     * @return
     */
    public static String generateSign(Map<String,Object> params,String appSecret){
        String paramsStr= paramsSortJoin(params);
        //拼接密钥后加密
        String allStr=paramsStr+appSecret;
        return md5(allStr);
    }


    /**
     * JSON对象转Map 兼容POST JSON请求体签名
     * @param jsonData
     * @return
     */
    public static Map<String,Object> jsonToMap(Object jsonData){
        try{
            return  OBJECT_MAPPER.convertValue(jsonData
                    , new TypeReference<Map<String,Object>>() {});
        }catch (Exception e){
            return new HashMap<>();
        }
    }

    public  static boolean verifySign(Map<String,Object> params,String appSecret,String clientSign){
        if(StringUtils.isEmpty(clientSign)){
            return false;
        }

        String serverSign = generateSign(params, appSecret);

        return serverSign.equals(clientSign);
    }
}
