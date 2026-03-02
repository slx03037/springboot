package com.doris.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doris.basic.entity.UserInfo;
import com.doris.basic.mapper.UserInfoMapper;
import com.doris.basic.service.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author shenlx
 * @description 用户信息
 * @date 2026/2/10 14:46
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    /**
     * 批量插入用户数据（Doris 批量操作，禁用事务也可，Doris 支持批量原子性）
     * @param userList 用户列表
     * @return 插入行数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsertUser(List<UserInfo> userList) {
        // 分批插入（避免单批次数据量过大，推荐每批 1000~5000 条）
        int batchSize = 2000;
        int total = 0;
        for (int i = 0; i < userList.size(); i += batchSize) {
            int end = Math.min(i + batchSize, userList.size());
            List<UserInfo> subList = userList.subList(i, end);
            total += baseMapper.batchInsert(subList);
        }
        return total;
    }
}
