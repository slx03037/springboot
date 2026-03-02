package com.doris.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.doris.basic.entity.ItemVector;

import java.util.List;

/**
 * @author shenlx
 * @description 商品向量
 * @date 2026/2/10 14:51
 */
public interface ItemVectorService extends IService<ItemVector> {

    /**
     * 查询 TOP N 相似商品（基于 ANN 向量索引）
     * @param targetVec 目标向量
     * @param limit 返回条数
     * @return 相似商品列表
     */

    public List<ItemVector> getSimilarItem(String targetVec, Integer limit);
}
