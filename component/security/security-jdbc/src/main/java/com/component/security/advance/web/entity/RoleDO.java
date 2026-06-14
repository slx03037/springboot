package com.component.security.advance.web.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author shenlx
 * @description
 * @date 2026/6/14 11:57
 */
@Data
public class RoleDO {

    private Long id;

    private String name;

    private String roleKey;

    private Integer status;

    private Integer delFlag;

    private String createBy;

    private String createName;

    private Date createTime;

    private String updateName;

    private Date updateTime;

    private String remark;
}
