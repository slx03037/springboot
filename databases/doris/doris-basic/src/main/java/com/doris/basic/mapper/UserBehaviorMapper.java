package com.doris.basic.mapper;

import com.doris.basic.entity.UserBehavior;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author shenlx
 * @description
 * @date 2026/2/10 14:20
 */
@Mapper
public interface UserBehaviorMapper {

    @Select("SELECT * FROM user_behavior WHERE user_id = #{userId}")
    List<UserBehavior> selectByUserId(@Param("userId") Long userId);

    @Insert("INSERT INTO user_behavior " +
            "(user_id, item_id, category_id, behavior_type, behavior_time) " +
            "VALUES (#{userId}, #{itemId}, #{categoryId}, #{behaviorType}, #{behaviorTime})")
    int insert(UserBehavior behavior);

    @Select("SELECT user_id, COUNT(*) as cnt " +
            "FROM user_behavior " +
            "WHERE behavior_time >= #{startTime} " +
            "GROUP BY user_id " +
            "ORDER BY cnt DESC " +
            "LIMIT #{limit}")
    List<Map<String, Object>> getUserBehaviorRank(
            @Param("startTime") String startTime,
            @Param("limit") int limit);
}
