package com.opendragon.community.controller;

import com.opendragon.community.dto.QuestionDTO;
import com.opendragon.community.mapper.QuestionMapper;
import com.opendragon.community.mapper.UserMapper;
import com.opendragon.community.model.Question;
import com.opendragon.community.model.User;
import com.opendragon.community.service.QuestionService;
import com.opendragon.community.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model){
        User user = Utils.CheckUserToken(request, userMapper);

        if(user != null){
            request.getSession().setAttribute("user", user);
        }

        List<QuestionDTO> questions = questionService.list();
        model.addAttribute("questions", questions);

        return "index";
    }
}

