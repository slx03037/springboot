package com.doris.basic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author shenlx
 * @description 用户信息实体类（对应 Doris 的 user_info 表）
 * @date 2026/2/10 14:34
 */

@Data
@TableName("user_info")
public class UserInfo {

    /**
     * 主键（Doris Primary Key）
     */
    @TableId(type = IdType.AUTO) // 若 Doris 主键自增，否则用 INPUT
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
