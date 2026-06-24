package com.word.poi.tl.web.manage.impl;


import com.deepoove.poi.data.ChartMultiSeriesRenderData;
import com.deepoove.poi.data.ChartSingleSeriesRenderData;
import com.deepoove.poi.data.SeriesRenderData;
import com.word.poi.tl.web.config.GenerateWordFactory;
import com.word.poi.tl.web.enums.WordContentTypeEnum;
import com.word.poi.tl.web.manage.GenerateWord;
import com.word.poi.tl.web.model.ChartSeriesRenderData;
import com.word.poi.tl.web.model.LabelData;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @program: my-spring-all-com.xinwen.mybatis.node01.service
 * @description:
 * @author: shenlx
 * @create: 2023-07-12 22:56
 **/

@Component
public class ChartGenerateWord implements GenerateWord {
    @PostConstruct
    private void init(){
        GenerateWordFactory.register(WordContentTypeEnum.CHART,this);
    }
    @Override
    public Object generateWord(LabelData obj) {
        ChartSeriesRenderData renderData  = (ChartSeriesRenderData) obj;
        if (Objects.nonNull(renderData.getCharType()) && Objects.equals("Single",renderData.getCharType().getType())){
            ChartSingleSeriesRenderData singleSeriesRenderData = new ChartSingleSeriesRenderData();
            singleSeriesRenderData.setCategories(renderData.getCategories());
            singleSeriesRenderData.setChartTitle(renderData.getTitle());
            ChartSeriesRenderData.RenderData seriesData = renderData.getSenderData().get(0);
            SeriesRenderData srd = new SeriesRenderData(seriesData.getRenderTitle(),seriesData.getData());
            if (Objects.nonNull(seriesData.getComboType())) {
                srd.setComboType(seriesData.getComboType());
            }
            singleSeriesRenderData.setSeriesData(srd);
            return singleSeriesRenderData;
        } else {
            ChartMultiSeriesRenderData seriesRenderData = new ChartMultiSeriesRenderData();
            seriesRenderData.setCategories(renderData.getCategories());
            seriesRenderData.setChartTitle(renderData.getTitle());
            List<ChartSeriesRenderData.RenderData> renderDataList = renderData.getSenderData();
            List<SeriesRenderData> groupData = new ArrayList<>();
            renderDataList.forEach(data -> {
                SeriesRenderData srd = new SeriesRenderData(data.getRenderTitle(),data.getData());
                if (Objects.nonNull(data.getComboType())){
                    srd.setComboType(data.getComboType());
                }
                groupData.add(srd);
            });
            seriesRenderData.setSeriesDatas(groupData);
            return seriesRenderData;
        }
    }
}
