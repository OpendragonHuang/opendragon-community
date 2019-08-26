package com.opendragon.community.controller;

import com.opendragon.community.dto.PageInformation;
import com.opendragon.community.mapper.UserMapper;
import com.opendragon.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author opend
 * @version 1.0
 * @date 2019/7/25
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name="page", required = false, defaultValue = "1") long page,
                        @RequestParam(name="size", required = false, defaultValue = "5") long size){
        PageInformation pageInformation = questionService.findWithRowbounds(page, size);

        model.addAttribute("pageInformation", pageInformation);

        return "index";
    }
}

