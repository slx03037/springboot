package com.tools.sign.basic.web.entity;

/**
 * @author shenlx
 * @description
 * @date 2026/5/9 14:55
 */

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("sys_app_client")
public class AppClient {
            @TableId(type = IdType.AUTO)
    private Long id;
            private String appKey;
            private String appSecret;
            private String appName;
            private Integer status;
            private Integer limitCount;
            @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
            @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
        }
