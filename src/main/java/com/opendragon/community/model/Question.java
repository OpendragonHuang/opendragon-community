package com.opendragon.community.model;

import lombok.Data;

/**
 * @author opend
 * @version 1.0
 * @date 2019/7/26
 */
@Data
public class Question {
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
}

