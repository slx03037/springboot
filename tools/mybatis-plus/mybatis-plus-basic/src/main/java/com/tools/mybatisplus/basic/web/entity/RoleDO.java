package com.tools.mybatisplus.basic.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author shenlx
 * @description
 * @date 2026/6/20 21:28
 */
@Data
@TableName("tb_role")
public class RoleDO {
    @TableId(type= IdType.AUTO)
    private Long id;

    /**
     * role name.
     */
    private String name;

    /**
     * role key.
     */
    private String roleKey;

    /**
     * description.
     */
    private String description;

    /**
     * create date time.
     */
    private LocalDateTime createTime;

    /**
     * update date time.
     */
    private LocalDateTime updateTime;

}
