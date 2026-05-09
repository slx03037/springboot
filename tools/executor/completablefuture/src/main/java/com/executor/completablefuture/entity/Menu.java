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
@Table(name = "t_menu")
public class Menu implements Serializable {
    private static final long serialVersionUID = -4218138157037085142L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;

    private String name;

    public Menu(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Menu( String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Menu() {

    }
}
