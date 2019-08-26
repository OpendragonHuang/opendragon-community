package com.opendragon.community.controller;

import com.opendragon.community.dto.PageInformation;
import com.opendragon.community.mapper.UserMapper;
import com.opendragon.community.model.User;
import com.opendragon.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String questions(HttpServletRequest request,
                            @PathVariable(name = "action") String action,
                            Model model,
                            @RequestParam(name="page", required = false, defaultValue = "1") long page,
                            @RequestParam(name="size", required = false, defaultValue = "5") long size){
        User user = (User) request.getSession().getAttribute("user");

        PageInformation pageInformation;
        if(action.equals("questions")){
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的问题");
            pageInformation = questionService.findWithRowboundsByCreatorId(user.getId(), page, size);
            model.addAttribute("pageInformation", pageInformation);
        }else if(action.equals("replies")){
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "我回复的问题");
        }



        return "profile";
    }
}
