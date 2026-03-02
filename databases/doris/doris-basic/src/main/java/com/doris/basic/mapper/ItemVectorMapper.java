package com.doris.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.doris.basic.entity.ItemVector;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author shenlx
 * @description 商品向量
 * @date 2026/2/10 14:43
 */
public interface ItemVectorMapper extends BaseMapper<ItemVector> {

    List<ItemVector> selectSimilarItemByVec(@Param("targetVec") String targetVec, @Param("limit") Integer limit);
}
