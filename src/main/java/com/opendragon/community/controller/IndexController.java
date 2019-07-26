package com.opendragon.community.controller;

import com.opendragon.community.mapper.UserMapper;
import com.opendragon.community.model.User;
import com.opendragon.community.util.Utils;
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
        User user = Utils.CheckUserToken(request, userMapper);

        if(user != null){
            request.getSession().setAttribute("user", user);
        }

        return "index";
    }
}

