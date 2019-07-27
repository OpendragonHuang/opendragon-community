package com.opendragon.community.dto;

import com.opendragon.community.model.User;
import lombok.Data;

/**
 * @Author opend
 * @Date 2019/7/26
 */
@Data
public class QuestionDTO {
    private int id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private int creator;
    private int commentCount;
    private int viewCount;
    private int likeCount;
    private String tag;
    private User user;
}
