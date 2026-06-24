package com.word.poi.tl.web.manage.impl;


import com.deepoove.poi.data.NumbericRenderData;
import com.word.poi.tl.web.config.GenerateWordFactory;
import com.word.poi.tl.web.enums.WordContentTypeEnum;
import com.word.poi.tl.web.manage.GenerateWord;
import com.word.poi.tl.web.model.LabelData;
import com.word.poi.tl.web.model.ListRenderData;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @program: my-spring-all-com.xinwen.mybatis.node01.service
 * @description:
 * @author: shenlx
 * @create: 2023-07-12 22:54
 **/

@Component
public class ListGenerateWord implements GenerateWord {
    @PostConstruct
    private void init(){
        GenerateWordFactory.register(WordContentTypeEnum.LIST,this);
    }
    @Override
    public Object generateWord(LabelData data) {
        ListRenderData listData =  (ListRenderData) data;
        return new NumbericRenderData(listData.getPair(),listData.getList());
    }
}
