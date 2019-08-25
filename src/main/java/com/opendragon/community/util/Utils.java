package com.opendragon.community.util;

import com.opendragon.community.mapper.UserMapper;
import com.opendragon.community.model.User;
import com.opendragon.community.model.UserExample;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
                    UserExample userExample = new UserExample();
                    userExample.createCriteria().andTokenEqualTo(cookie.getValue());
                    List<User> userList = userMapper.selectByExample(userExample);
                    User user = null;
                    if(userList.size() > 0){
                        user = userMapper.selectByExample(userExample).get(0);
                    }
                    return user;
                }
            }
        }
        return null;
    }
}

