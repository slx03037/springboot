package com.common.utils.exception;


import com.common.utils.model.interfaces.EnumInterface;

import java.text.MessageFormat;

/**
 * @author shenlx
 * @description
 * @date 2024/11/11 10:17
 */
public class BusinessException extends BaseException {
    private static final long serialVersionUID = 5947378634074028655L;
    public static final int DEFAULT_CODE = 9999;
    public static final String DEFAULT_MSG = "系统错误！";

    /**
     * 参数检验异常
     */
    public static final int PARAMS_CHECK_ERROR = 10001;



    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Integer code, String message) {
        super(code, message);
    }

    public static BaseException newInstance(String msgFormat, Object... args) {
        return new BusinessException(MessageFormat.format(msgFormat, args));
    }

    public BusinessException(EnumInterface enumInterface) {
        super(enumInterface.getCode(), enumInterface.getMsg());
        this.code = enumInterface.getCode();
        this.msg = enumInterface.getMsg();
    }


    public BusinessException(EnumInterface enumInterface, Object... args) {
        super(enumInterface.getCode(), MessageFormat.format(enumInterface.getMsg(), args));
    }

}
