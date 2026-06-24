package com.rpc.dubbo.realize.web.node06.sdk.model;

import java.io.Serializable;

/**
 * @author shenlx
 * @description
 * @date 2025/1/7 17:22
 */
public class Result<T>  implements Serializable {


    private static final long serialVersionUID = -8320659544286827584L;
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
