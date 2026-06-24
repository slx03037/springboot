package com.word.poi.tl.web.model;


import com.deepoove.poi.data.SeriesRenderData;
import com.word.poi.tl.web.enums.CharCombinationType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @program: my-spring-all-com.xinwen.mybatis.node01.service
 * @description:
 * @author: shenlx
 * @create: 2023-07-12 22:54
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class ChartSeriesRenderData extends LabelData {
    /**
     * 横轴数据
     */
    private String[] categories;

    /**
     * 图表名称
     */
    private String title;

    /**
     * 图表类型 组合
     */
    private CharCombinationType charType = CharCombinationType.MULTI;

    /**
     * 系列对应数据
     */
    private List<RenderData> senderData;

    @Data
    public static class RenderData{
        /**
         * 系列名称
         */
        private String renderTitle;
        /**
         * 系列对应的数据
         */
        private Number[] data;
        /**
         * 该系列对应生成的图表类型
         */
        private SeriesRenderData.ComboType comboType = null;
    }
}
