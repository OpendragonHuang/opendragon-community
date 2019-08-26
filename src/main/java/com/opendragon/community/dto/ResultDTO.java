package com.opendragon.community.dto;

import com.opendragon.community.exception.CustomizeErrorCode;
import com.opendragon.community.exception.ICustomizeErrorCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author opendragonhuang
 * @version 1.0
 * @date 2019/8/26
 */
@Data
public class ResultDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String message;


    public ResultDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResultDTO getInstance(Integer code, String message){
        ResultDTO resultDTO = new ResultDTO(code, message);
        return resultDTO;
    }

    public static ResultDTO errorOf(ICustomizeErrorCode noLogin) {
        return getInstance(noLogin.getCode(), noLogin.getMessage());
    }

    public static ResultDTO ok(ICustomizeErrorCode ok){
        return getInstance(ok.getCode(), ok.getMessage());
    }
}
