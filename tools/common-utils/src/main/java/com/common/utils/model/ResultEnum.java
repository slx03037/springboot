package com.common.utils.model;


import com.common.utils.model.interfaces.EnumInterface;

/**
 * @author shenlx
 * @description
 * @date 2024/11/11 10:18
 */
public enum ResultEnum implements EnumInterface {
    /**
     * 系统状态枚举
     */

    ADD_FAIL(1000, "新增失败"),
    UPDATE_FAIL(1001, "修改失败"),
    DELETE_FAIL(1002, "删除失败");

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;

    private String msg;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
