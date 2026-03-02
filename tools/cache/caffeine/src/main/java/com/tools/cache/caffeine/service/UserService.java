package com.tools.cache.caffeine.service;

import com.tools.cache.caffeine.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2025/1/21 11:21
 */
@Component
@Slf4j
public class UserService {

    private static final List<UserEntity> USERS=new ArrayList<>();

    public UserEntity findById(Integer userId){
        log.info("查库操作");
        for(UserEntity user:USERS){
            if(user.getUserId().equals(userId)){
                return user;
            }
        }
        return null;
    }

    public void setUserEntity(UserEntity userEntity){
        USERS.add(userEntity);
    }

}
