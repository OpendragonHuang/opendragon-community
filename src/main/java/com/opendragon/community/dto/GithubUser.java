package com.opendragon.community.dto;

import lombok.Data;

/**
 * @author opend
 * @version 1.0
 * @date 2019/7/25
 */
@Data
public class GithubUser {
    private long id;
    private String login;
    private String name;
    private String avatarUrl;
}

