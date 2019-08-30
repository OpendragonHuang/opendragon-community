package com.opendragon.community.dto;

import com.opendragon.community.model.User;
import lombok.Data;

import java.io.Serializable;

/**
 * @author opendragonhuang
 * @version 1.0
 * @date 2019/8/30
 */
@Data
public class CommentResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long parentId;
    private Integer type;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer likeCount;
    private String comment;
    private Integer subCommentCount;
    private User user;
}
