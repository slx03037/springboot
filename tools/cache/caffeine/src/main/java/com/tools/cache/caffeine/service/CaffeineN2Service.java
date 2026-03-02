package com.tools.cache.caffeine.service;


import com.tools.cache.caffeine.common.CacheConstants;
import com.tools.cache.caffeine.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author shenlx
 * @description
 * @date 2025/1/21 10:35
 */
@Component
@Slf4j
public class CaffeineN2Service {
    @Autowired
    private UserService userService;

    /**
     * value：缓存key的前缀
     *  key：缓存key的后缀。
     *  sync：设置如果缓存过期是不是只放一个请求去请求数据库，其他请求阻塞，默认是false（根据个人需求）
     *  unless：不缓存空值,这里不使用，会报错
     *  查询用户信息类
     *  如果需要加自定义字符串，需要用单引号
     *  如果查询为null，也会被缓存
     */

   // 这里要注意的是Cache和@Transactional一样也使用了代理，类内调用将失效
    @Cacheable(value = CacheConstants.GET_USER,key ="'user'+#userId",sync =true)
    @CacheEvict
    public UserEntity getUserByUserId(Integer userId){
        UserEntity userEntity = userService.findById(userId);
        log.info("查询了数据库：{}",userEntity);
        return userEntity;
    }

    public void addUserEntity(){
        userService.setUserEntity(new UserEntity(1,"张三",20));
        userService.setUserEntity(new UserEntity(2,"里斯",21));
        userService.setUserEntity(new UserEntity(3,"王二",22));
    }


}
