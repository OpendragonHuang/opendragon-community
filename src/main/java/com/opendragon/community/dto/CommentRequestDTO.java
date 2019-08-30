package com.opendragon.community.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author opendragonhuang
 * @version 1.0
 * @date 2019/8/26
 */
@Data
public class CommentRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long parentId;
    private Integer type;
    private Integer commentator;
    private String comment;
}
