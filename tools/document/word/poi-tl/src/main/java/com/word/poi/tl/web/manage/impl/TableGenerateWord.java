package com.word.poi.tl.web.manage.impl;


import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.word.poi.tl.web.config.GenerateWordFactory;
import com.word.poi.tl.web.enums.WordContentTypeEnum;
import com.word.poi.tl.web.manage.GenerateWord;
import com.word.poi.tl.web.model.LabelData;
import com.word.poi.tl.web.model.TableSeriesRenderData;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: my-spring-all-com.xinwen.mybatis.node01.service
 * @description:  列封装类
 * @author: shenlx
 * @create: 2023-07-12 22:51
 **/

@Component
public class TableGenerateWord implements GenerateWord {
    @PostConstruct
    private void init(){
        GenerateWordFactory.register(WordContentTypeEnum.TABLE,this);
    }
    @Override
    public Object generateWord(LabelData data) {
        TableSeriesRenderData tableData = (TableSeriesRenderData) data;
        RowRenderData header = RowRenderData.build(tableData.getHeader());
        List<RowRenderData> contentData = new ArrayList<>();
        tableData.getContents().forEach(con ->{
            contentData.add(RowRenderData.build(con));
        });
        return new MiniTableRenderData(header,contentData);
    }
}
