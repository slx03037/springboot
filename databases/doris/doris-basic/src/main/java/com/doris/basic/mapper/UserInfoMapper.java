package com.doris.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.doris.basic.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author shenlx
 * @description 用户信息
 * @date 2026/2/10 14:35
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    /**
     * 批量插入用户数据（Doris 批量操作性能最优）
     */
    int batchInsert(@Param("list") List<UserInfo> userList);
}
