package com.tools.sign.basic.web.common;

import lombok.Data;

/**
 * @author shenlx
 * @description
 * @date 2026/5/9 10:50
 */



@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg("操作成功");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.setCode(400);
        result.setMsg(msg);
        return result;
    }

    /**
     * 未授权专用状态码
     */
    public static <T> Result<T> unAuth(String msg) {
        Result<T> result = new Result<>();
        result.setCode(401);
        result.setMsg(msg);
        return result;
    }
}