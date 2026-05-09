package com.tools.jwt.basic.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author shenlx
 * @description
 * @date 2026/4/23 9:32
 */
@Data
public class UserInfoVo implements Serializable {
    private static final long serialVersionUID = -4241531233929629477L;
    private Long id;

    private String username;

    private String nickName;
}
