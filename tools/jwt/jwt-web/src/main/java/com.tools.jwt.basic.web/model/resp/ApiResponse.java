package com.tools.jwt.basic.web.model.resp;

import lombok.Getter;
import lombok.Setter;

/**
 * @author shenlx
 * @description
 * @date 2026/5/11 10:55
 */
@Getter
@Setter
public class ApiResponse <T>{
    private Integer code;
    private String msg;
    private T data;

    public ApiResponse(Integer code,String msg,T data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public static <T> ApiResponse<T> error(Integer code,String msg,T data){
        return new ApiResponse<>( code, msg, data);
    }

    public static <T> ApiResponse<T> error(Integer code,String msg){
        return new ApiResponse<>( code, msg, null);
    }

    public static <T> ApiResponse<T> error(String msg){
        return new ApiResponse<>( 500, msg, null);
    }
    public static <T> ApiResponse<T> success(T data){
        return new ApiResponse<>( 200, "操作成功", data);
    }

    public static <T> ApiResponse<T> success(){
        return new ApiResponse<>( 200, "操作成功", null);
    }
}

