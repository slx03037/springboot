package com.tools.mybatisplus.basic.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/6/20 21:57
 */
@Data
@TableName("tb_user")
public class UserDO {
    @TableId(type= IdType.AUTO)
    private Long id;

    /**
     * username.
     */
    private String userName;

    /**
     * user pwd.
     */
    private String password;

    /**
     * email.
     */
    private String email;

    /**
     * phoneNumber.
     */
    private long phoneNumber;

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

    @TableField(exist = false)
    private List<RoleDO> roles;
}
