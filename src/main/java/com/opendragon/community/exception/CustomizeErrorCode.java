package com.opendragon.community.exception;

/**
 * @author opendragonhuang
 * @version 1.0
 * @date 2019/8/26
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    OK(2000, "成功"),
    QUESTION_NOT_FIND(2001, "问题没有找到，要不稍后再试试"),
    TARGET_COMMENT_NOT_FIND(2002, "目标评论没有找到"),
    NO_LOGIN(2003, "用户未登录"),
    SYS_ERR(2004, "系统错误"),
    TYPE_PARAM_WORN(2005, "类型错误"),
    COMMENT_NOT_FIND(2006, "评论未找到");

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
