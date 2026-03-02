package com.doris.basic;

import com.doris.basic.entity.ItemVector;
import com.doris.basic.entity.UserInfo;
import com.doris.basic.service.ItemVectorService;
import com.doris.basic.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/2/11 9:59
 */
@SpringBootTest
@Slf4j
public class ApplicationTest {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ItemVectorService itemVectorService;

    @Test
    public void batchInsertUser(){
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
        log.info("批量插入成功，共插入 " + count + " 条数据"); ;
    }


    @Test
    public void getSimilarItem(@RequestParam String targetVec) {
        // 调用向量查询
        List<ItemVector> similarItem = itemVectorService.getSimilarItem(targetVec, 10);
        for (ItemVector item:similarItem){
            log.info("ItemVector:{}",item);
        }

    }
}
