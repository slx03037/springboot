package com.doris.basic.controller;

import com.doris.basic.entity.ItemVector;
import com.doris.basic.entity.UserInfo;
import com.doris.basic.service.ItemVectorService;
import com.doris.basic.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/2/10 14:57
 */

@RestController
@RequestMapping("/doris")
public class DorisTestController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ItemVectorService itemVectorService;

    /**
     * 批量插入用户数据
     */
    @PostMapping("/user/batch")
    public String batchInsertUser() {
        // 构造测试数据
        List<UserInfo> userList = new ArrayList<>();
        for (long i = 1; i <= 1000; i++) {
            UserInfo user = new UserInfo();
            user.setId(i);
            user.setUserName("test_user_" + i);
            user.setPhone("1380013800" + (i % 10));
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            userList.add(user);
        }
        // 批量插入
        int count = userInfoService.batchInsertUser(userList);
        return "批量插入成功，共插入 " + count + " 条数据";
    }

    /**
     * 查询 TOP10 相似商品
     * @param targetVec 目标向量（示例：128维）
     * @return 相似商品列表
     */
    @GetMapping("/item/similar")
    public List<ItemVector> getSimilarItem(@RequestParam String targetVec) {
        // 调用向量查询
        return itemVectorService.getSimilarItem(targetVec, 10);
    }
}
