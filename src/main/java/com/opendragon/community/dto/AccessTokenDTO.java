package com.opendragon.community.dto;

import lombok.Data;

/**
 * @author opend
 * @version 1.0
 * @date 2019/7/25
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}

