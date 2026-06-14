package com.component.security.advance.web.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author shenlx
 * @description
 * @date 2026/6/12 21:43
 */
// 菜单表(Menu)实体类
@TableName(value="sys_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDO {

    @TableId
    private Long id;
    private String menuName;        // 菜单名
    private String path;            // 路由地址
    private String component;       // 组件路径
    private String visible;         // 菜单状态（0显示 1隐藏）
    private String status;          // 菜单状态（0正常 1停用）
    private String perms;           // 权限标识
    private String icon;            // 菜单图标
    private Long createBy;          // 创建人
    private Date createTime;        // 创建时间
    private Long updateBy;          // 更新人
    private Date updateTime;        // 更新时间
    private Integer delFlag;        // 是否删除（0未删除 1已删除）
    private String remark;          // 备注
}
