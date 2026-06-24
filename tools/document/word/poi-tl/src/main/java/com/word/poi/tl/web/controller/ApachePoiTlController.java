package com.word.poi.tl.web.controller;


import com.word.poi.tl.web.service.ApachePoitlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: my-spring-all-com.xinwen.mybatis.node01.service
 * @description:
 * @author: shenlx
 * @create: 2023-07-12 23:16
 **/

@RestController
@RequestMapping("/poitl")
public class ApachePoiTlController {

    @Autowired
    private ApachePoitlService poitlService;

    @GetMapping("/generateCharts")
    public void generateCharts(){
        poitlService.generateCharts();
    }
}
