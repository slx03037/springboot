package com.word.poi.tl.web.manage.impl;


import com.word.poi.tl.web.config.GenerateWordFactory;
import com.word.poi.tl.web.enums.WordContentTypeEnum;
import com.word.poi.tl.web.manage.GenerateWord;
import com.word.poi.tl.web.model.LabelData;
import com.word.poi.tl.web.model.TextContentData;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

/**
 * @program: my-spring-all-com.xinwen.mybatis.node01.service
 * @description: 文本封装
 * @author: shenlx
 * @create: 2023-07-12 22:45
 **/

@Component
public class TextGenerateWord implements GenerateWord {

    @PostConstruct
    public void init(){
        GenerateWordFactory.register(WordContentTypeEnum.TEXT,this);
    }

    @Override
    public Object generateWord(LabelData data) {
        TextContentData contentData = (TextContentData) data;
        return Objects.nonNull(contentData.getLinkData()) ? contentData.getLinkData() :
                Objects.nonNull(contentData.getRenderData()) ? contentData.getRenderData() : contentData.getContent();
    }
}
