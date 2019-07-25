package com.opendragon.community.controller;

import com.opendragon.community.mapper.UserMapper;
import com.opendragon.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author opend
 * @version 1.0
 * @date 2019/7/25
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                User user = userMapper.findByToken(cookie.getValue());
                if(user != null){
                    request.getSession().setAttribute("user", user);
                }
                break;
            }

        }
        return "index";
    }
}

