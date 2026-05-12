package com.tools.desgin.structure.decorator.node03.service;

import com.xinwen.desgin.structure.decorator.node03.handler.BaseHandler;
import com.xinwen.desgin.structure.decorator.node03.pojo.CertificatePojo;
import org.springframework.stereotype.Service;

/**
 * @author shenlx
 * @description
 * @date 2024/8/30 14:15
 */
@Service
public class CertificateService implements BaseHandler<CertificatePojo,Integer> {

    /**
     * 处理导入证数的核心逻辑
     */
    @Override
    public Integer handler(CertificatePojo certificatePojo) {
        System.out.println("核心业务，证书数据"+certificatePojo);

        int maxValue = Integer.MAX_VALUE;
        return maxValue;
    }
}
