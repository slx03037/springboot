package com.common.utils.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @author shenlx
 * @description
 * @date 2024/11/11 10:19
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -267510024748900679L;


    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回的的数据
     */
    private T data;

    /**
     * 描述
     */
    private String msg;

    private static Integer successCode=200;

    private static int errorCode=500;

    private static String successMsg="操作成功";

    private static String errorMsg="操作失败";


    public static <T> Result<T> success() {
        return new Result<>(successCode, null, successMsg);
    }

    public static <T> Result<T> success(String msg) {
        return new Result<>(successCode, null, msg);
    }

//    public static <T> Result<T> success(T data) {
//        return new Result<>(successCode, data, successMsg);
//    }

    public static <T> Result<T> success(T data) {
        return new Result<>(successCode, data, successMsg);
    }

    public static <T> Result<T> error(T data) {
        return new Result<>(errorCode, data, errorMsg);
    }

    public static <T> Result<T> error(Integer code, T data) {
        return new Result<>(code, data, errorMsg);
    }

    public static <T> Result<T> error(ResultEnum errorEnum) {
        return new Result<>(errorEnum.getCode(), null, errorEnum.getMsg());
    }

    public static <T> Result<T> error(ResultEnum errorEnum,Object msg) {
        return new Result<>(errorEnum.getCode(), null, String.format(errorEnum.getMsg(),msg));
    }

    public static <T> Result<T> error(Integer code, String msg) {
        return new Result<>(code, null, msg);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(errorCode, null, msg);
    }

    private Result() {
    }

    private Result(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
}
