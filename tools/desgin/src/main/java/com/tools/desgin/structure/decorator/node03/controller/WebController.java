package com.tools.desgin.structure.decorator.node03.controller;

import com.xinwen.desgin.structure.decorator.node03.enums.SceneConstants;
import com.xinwen.desgin.structure.decorator.node03.handler.AbstractHandler;
import com.xinwen.desgin.structure.decorator.node03.manager.DecorateManager;
import com.xinwen.desgin.structure.decorator.node03.service.CertificateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author shenlx
 * @description
 * @date 2024/8/30 15:09
 */
@RestController
public class WebController {
    @Resource
    private DecorateManager decorateManager;

    @Resource
    private CertificateService certificateService;

    @PostMapping("/import")
    public Integer importFile(@RequestParam MultipartFile file,@RequestParam String productCode){
        AbstractHandler handler= decorateManager.selectHandler(SceneConstants.CERTIFICATE_IMPORT,productCode);
        if(Objects.isNull(handler)){
            return 0;
        }
        handler.setService(certificateService);
        return (Integer) handler.handler(file);
    }
}
