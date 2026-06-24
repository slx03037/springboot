package com.word.poi.tl.web.model;


import com.word.poi.tl.web.enums.WordContentTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: my-spring-all-com.xinwen.mybatis.node01.service
 * @description: 公共实体
 * @author: shenlx
 * @create: 2023-07-12 22:40
 **/

@Data
@Accessors(chain = true)
public class LabelData {
    /**
     * 标签名称，即put使用到的名称
     */
    private String labelName;
    /**
     * 文件内容类型
     */
    private WordContentTypeEnum typeEnum;
}
