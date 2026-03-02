package com.doris.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doris.basic.entity.ItemVector;
import com.doris.basic.mapper.ItemVectorMapper;
import com.doris.basic.service.ItemVectorService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/2/10 14:52
 */
@Service
public class ItemVectorServiceImpl extends ServiceImpl<ItemVectorMapper, ItemVector> implements ItemVectorService {

    /**
     * 查询 TOP N 相似商品（基于 ANN 向量索引）
     * @param targetVec 目标向量
     * @param limit 返回条数
     * @return 相似商品列表
     */
    @Override
    public List<ItemVector> getSimilarItem(String targetVec, Integer limit) {
        return baseMapper.selectSimilarItemByVec(targetVec, limit);
    }
}
