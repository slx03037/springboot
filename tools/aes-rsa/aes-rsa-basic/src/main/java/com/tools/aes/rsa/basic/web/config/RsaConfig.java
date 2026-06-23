package com.tools.aes.rsa.basic.web.config;

import com.tools.aes.rsa.basic.web.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Base64Utils;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author shenlx
 * @description
 * @date 2026/6/23 14:16
 */
@Configuration
public class RsaConfig {
    @Value("${rsa.public-key}")
    private String publicKeyStr;

    @Value("${rsa.private-key}")
    private String privateKeyStr;

    // 初始化RSA公钥
    @Bean
    public PublicKey rsaPublicKey() throws Exception {
        return EncryptUtils.stringToPublicKey(publicKeyStr);
    }

    // 初始化RSA私钥
    @Bean
    public PrivateKey rsaPrivateKey() throws Exception {
        return EncryptUtils.stringToPrivateKey(privateKeyStr);
    }

    // 生成RSA密钥对（测试用，生产环境提前生成并配置）
    public static void main(String[] args) throws Exception {
        KeyPair keyPair = EncryptUtils.generateRsaKeyPair();
        String publicKey = new String(Base64Utils.encode(keyPair.getPublic().getEncoded()));
        String privateKey = new String(Base64Utils.encode(keyPair.getPrivate().getEncoded()));
        System.out.println("RSA公钥：" + publicKey);
        System.out.println("RSA私钥：" + privateKey);
    }
}
