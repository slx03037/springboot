package com.tools.desgin.structure.decorator.node03.decorate;

import com.xinwen.desgin.structure.decorator.node03.annotaion.Decorate;
import com.xinwen.desgin.structure.decorator.node03.enums.DecorateConstants;
import com.xinwen.desgin.structure.decorator.node03.enums.SceneConstants;
import com.xinwen.desgin.structure.decorator.node03.handler.AbstractHandler;
import com.xinwen.desgin.structure.decorator.node03.pojo.CertificatePojo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author shenlx
 * @description
 * @date 2024/8/30 10:56
 */
@Decorate(scene = SceneConstants.CERTIFICATE_IMPORT, type = DecorateConstants.PRODUCT_A)
public class AProductServiceDecorate extends AbstractHandler<MultipartFile,Integer> {
    @Override
    public Integer handler(MultipartFile file) {

        return null;
    }

    public CertificatePojo parseData(MultipartFile file){
        //file，证书解析
        System.out.println("A产品的证数解析");
        CertificatePojo certificatePojo = new CertificatePojo();
        certificatePojo.setMobile("123");
        certificatePojo.setName("张三");
        certificatePojo.setMemberNo("req_123");
        certificatePojo.setEffectDate("2024-01-01 20:20:20");
        return certificatePojo;
    }

    public void check(CertificatePojo data){
        //数据规模和重复性校验
        System.out.println("A证书数据校验");
    }

    /**
     * 计算业绩信息
     */
    private void calAchievement(String mobile){
        System.out.println("查询用户信息:.手机号："+mobile);
        System.out.println("重新计算业绩");
    }
}
