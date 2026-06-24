package com.word.poi.tl.web.manage.impl;

import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.util.BytePictureUtils;
import com.word.poi.tl.web.config.GenerateWordFactory;
import com.word.poi.tl.web.enums.WordContentTypeEnum;
import com.word.poi.tl.web.manage.GenerateWord;
import com.word.poi.tl.web.model.LabelData;
import com.word.poi.tl.web.model.PictureContentData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @program: my-spring-all-com.xinwen.mybatis.node01.service
 * @description: 图片封装
 * @author: shenlx
 * @create: 2023-07-12 22:47
 **/

@Component
public class PictureGenerateWord implements GenerateWord {

    @PostConstruct
    private void init(){
        GenerateWordFactory.register(WordContentTypeEnum.PICTURE,this);
    }

    @Override
    public Object generateWord(LabelData data) {
        PictureContentData picture = (PictureContentData) data;
        return StringUtils.isNotBlank(picture.getPicUrl()) ? new PictureRenderData(picture.getWidth(),picture.getHeight(),picture.getPicType().getPicName(),
                BytePictureUtils.getUrlBufferedImage(picture.getPicUrl()))
                : new PictureRenderData(picture.getWidth(),picture.getHeight(),picture.getFile());
    }
}
