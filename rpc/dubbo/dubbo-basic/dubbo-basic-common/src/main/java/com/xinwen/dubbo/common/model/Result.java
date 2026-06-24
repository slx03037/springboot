package com.xinwen.dubbo.common.model;

import java.io.Serializable;

/**
 * @author shenlx
 * @description
 * @date 2025/1/7 17:22
 */
public class Result<T>  implements Serializable {
    private static final long serialVersionUID = -1569985846616571582L;

    private T data ;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSucess () {
        return success;
    }

    public void setSucess(boolean success) {
        this.success=success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private boolean success;

    private  String msg;
}
