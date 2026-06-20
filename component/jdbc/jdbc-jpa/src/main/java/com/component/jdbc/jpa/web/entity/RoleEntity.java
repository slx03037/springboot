package com.component.jdbc.jpa.web.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author shenlx
 * @description
 * @date 2026/6/20 20:39
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_role")
public class RoleEntity implements BaseEntity{

    private static final long serialVersionUID = -1599694065753280382L;
    /**
     * role id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
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
