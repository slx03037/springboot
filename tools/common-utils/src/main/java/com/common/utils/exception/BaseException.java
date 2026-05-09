package com.common.utils.exception;

import java.text.MessageFormat;

/**
 * @author shenlx
 * @description
 * @date 2024/11/11 10:17
 */
public class BaseException extends RuntimeException{
    private static final long serialVersionUID = -8591100372823873109L;
    protected Integer code;
    protected String msg;

    public BaseException(Throwable e)
    {
        super(e.getMessage(), e);
    }

    public BaseException() {
        super();
    }

    protected BaseException(String message) {
        super(message);
    }

    protected BaseException(Integer code, String msgFormat, Object... args) {
        super(MessageFormat.format(msgFormat, args));
        this.code = code;
        this.msg = MessageFormat.format(msgFormat, args);
    }

    public String getMsg() {
        return this.msg;
    }

    public Integer getCode() {
        return this.code;
    }
}

