package com.mysql.basic;

import com.alibaba.fastjson.JSON;
import com.mysql.basic.entity.UserDO;
import com.mysql.basic.repository.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author shenlx
 * @description
 * @date 2026/3/6 14:08
 */
@SpringBootTest
@Slf4j
public class ApplicationTest {
    @Autowired
    UserDao userDao;

    @Test
    public void add() {
        UserDO user=new UserDO();
         userDao.add(user);
    }

    @Test
    public void update() {
        UserDO user=new UserDO();
         userDao.update(user);
    }

    @Test
    public void delete() {
        String username="test";
       userDao.delete(username);
    }

    @Test
    public void queryMap() {
        List<Map<String, Object>> maps = userDao.queryForList();
        if (!maps.isEmpty()){
            for (Map<String, Object> map:maps){
                Set<String> keySet = map.keySet();
                for(String key:keySet){
                    System.out.println("key:"+key+"value:"+map.get(key));
                }

            }
        }

    }

    @Test
    public void query() {
        String username="ssoAdmin";
        UserDO query = userDao.query(username);
        System.out.println(JSON.toJSONString(query));
    }
}
