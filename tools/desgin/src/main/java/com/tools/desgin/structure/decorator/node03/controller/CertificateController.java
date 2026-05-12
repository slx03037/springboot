package com.tools.desgin.structure.decorator.node03.controller;

import com.xinwen.desgin.structure.decorator.node03.service.CommonCertificateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author shenlx
 * @description
 * @date 2024/8/30 10:05
 */
@RestController
@RequestMapping("/certificate")
public class CertificateController {
    @Resource
    private CommonCertificateService certificateService;

    @PostMapping("/import")
    public Integer importFile(@RequestParam MultipartFile file,@RequestParam String productCode){
        return certificateService.importCertificate(file,productCode);
    }
}
