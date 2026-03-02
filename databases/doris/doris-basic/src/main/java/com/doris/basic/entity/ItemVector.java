package com.doris.basic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author shenlx
 * @description 商品向量实体类（对应 Doris 的 item_vector 表）
 * @date 2026/2/10 14:35
 */

@Data
@TableName("item_vector")
public class ItemVector {

    /**
     * 商品ID（主键）
     */
    @TableId(type = IdType.INPUT)
    private Long itemId;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 商品特征向量（128维 FLOAT，Doris VECTOR 类型）
     * 存储格式："[0.12,0.35,...,0.43]"
     */
    private String itemVec;

    /**
     * 相似度查询时的距离（非表字段，仅用于返回结果）
     */
    private Float cosineDist;
}
