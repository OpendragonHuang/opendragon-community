package com.opendragon.community.model;

import lombok.Data;

/**
 * @author opend
 * @version 1.0
 * @date 2019/7/25
 */
@Data
public class User {
    private Integer id;
    private String accountId;
    private String name;
    private String avatarUrl;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
}

