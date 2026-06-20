package com.tools.mybatisplus.basic.web.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author shenlx
 * @description
 * @date 2026/6/20 20:57
 */
@Data
public class RoleQueryBean implements Serializable {
    private static final long serialVersionUID = -3872900697662136773L;
    private String name;

    private String description;

    private String roleKey;
}
