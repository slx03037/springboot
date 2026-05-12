package com.tools.desgin.structure.decorator.node03.service;

import com.xinwen.desgin.structure.decorator.node03.enums.DecorateConstants;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author shenlx
 * @description
 * @date 2024/8/30 10:06
 */
public class CommonCertificateService {
    public Integer importCertificate(MultipartFile file,String productCode){
        //参数非空校验
        //通过file后缀判断file类型，支持excel和pdf
        //解析file文件，获取数据，统一封装到定义的nCertificatePojo的类中
        //根据产品类型判断导入之前的业务数据
        if(productCode.equalsIgnoreCase(DecorateConstants.PRODUCT_A)){

        }else if(productCode.equalsIgnoreCase(DecorateConstants.PRODUCT_B))
        {

        }else if(productCode.equalsIgnoreCase(DecorateConstants.PRODUCT_C))
        {

        }else {

        }
        Integer agentId=Integer.MAX_VALUE;
        return agentId;
    }
}
