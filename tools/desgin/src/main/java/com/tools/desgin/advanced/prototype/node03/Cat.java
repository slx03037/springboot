package com.tools.desgin.advanced.prototype.node03;

import java.io.Serializable;

/**
 * @author shenlx
 * @description 深拷贝与浅拷贝
 * @date 2024/8/19 14:17
 */
public class Cat implements Serializable {
    private static final long serialVersionUID = 6279596927552942634L;

    public String name;

    public Cat(String name){
        this.name=name;
    }
}
