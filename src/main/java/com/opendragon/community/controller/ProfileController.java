package com.opendragon.community.controller;

import com.opendragon.community.mapper.UserMapper;
import com.opendragon.community.model.User;
import com.opendragon.community.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author opendragonhuang
 * @version 1.0
 * @date 2019/7/27
 */
@Controller
public class ProfileController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/profile/{action}")
    public String questions(HttpServletRequest request,
                            @PathVariable(name = "action") String action,
                            Model model){
        User user = Utils.CheckUserToken(request, userMapper);

        if(user != null){
            request.getSession().setAttribute("user", user);
        }

        if(action.equals("questions")){
            model.addAttribute("section", "question");
            model.addAttribute("sectionName", "我的问题");
        }

        return "profile";
    }
}
