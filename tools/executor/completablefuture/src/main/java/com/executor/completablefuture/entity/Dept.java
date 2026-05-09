package com.executor.completablefuture.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author shenlx
 * @description
 * @date 2024/5/17 15:34
 */
@Data
@Entity
@Table(name = "t_dept")
public class Dept implements Serializable {
    private static final long serialVersionUID = 5502641229516627438L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String deptName;

    private String deptCode;

    private Long parentId;

    public Dept(String deptName, String deptCode, Long parentId) {
        this.deptName = deptName;
        this.deptCode = deptCode;
        this.parentId = parentId;
    }

    public Dept() {

    }
}
