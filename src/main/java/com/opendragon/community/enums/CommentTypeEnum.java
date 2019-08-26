package com.opendragon.community.enums;

/**
 * @author opendragonhuang
 * @version 1.0
 * @date 2019/8/26
 */
public enum CommentTypeEnum{
    QUESTION(1),
    COMMENT(2);
    private Integer type;

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public static boolean isExists(Integer type) {
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if(value.getType() == type){
                return true;
            }
        }

        return false;
    }

    public Integer getType() {
        return type;
    }



}
