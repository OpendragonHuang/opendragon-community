package com.opendragon.community.util;

import com.opendragon.community.mapper.UserMapper;
import com.opendragon.community.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author opend
 * @version 1.0
 * @date 2019/7/26
 */
public class Utils {
    public static User CheckUserToken(HttpServletRequest request, UserMapper userMapper){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    User user = userMapper.findByToken(cookie.getValue());
                    return user;
                }
            }
        }
        return null;
    }
}

