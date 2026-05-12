package com.tools.desgin.advanced.prototype.node01;

import lombok.Data;

/**
 * @author shenlx
 * @description
 * @date 2024/8/16 10:49
 */
@Data
public abstract class Shape implements Cloneable{
    private String id;

    protected String type;

    abstract void draw();

    @Override
    public Object clone() {
        try {
            Shape clone = (Shape) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
