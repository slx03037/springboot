package com.executor.completablefuture.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author shenlx
 * @description
 * @date 2024/5/17 15:33
 */

@Data
@Entity
@Table(name = "t_user")
public class User implements Serializable {
    private static final long serialVersionUID = 3474144787122774211L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private Integer sex;

    public User(String name, Integer age, Integer sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public User() {

    }
}
