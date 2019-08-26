package com.opendragon.community.exception;

import java.io.Serializable;

/**
 * @author opendragonhuang
 * @version 1.0
 * @date 2019/8/26
 */
public class CustomizeException extends RuntimeException implements ICustomizeErrorCode, Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode(){
        return code;
    }
}
