package com.doris.basic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author shenlx
 * @description
 * @date 2026/2/10 12:08
 */
@Data
@TableName("user_behavior")
public class UserBehavior {

    @TableId(value = "user_id", type = IdType.INPUT)
    private Long userId;

    private String itemId;
    private String categoryId;
    private String behaviorType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime behaviorTime;
}
