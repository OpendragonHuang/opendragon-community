package com.opendragon.community.controller;

import com.opendragon.community.mapper.QuestionMapper;
import com.opendragon.community.mapper.UserMapper;
import com.opendragon.community.model.Question;
import com.opendragon.community.model.User;
import com.opendragon.community.service.QuestionService;
import com.opendragon.community.util.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author opend
 * @version 1.0
 * @date 2019/7/26
 */
@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Question question = questionService.findById(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(HttpServletRequest request){
        return "publish";
    }

    @PostMapping("/publish")
    public String publish(@RequestParam(name="title") String title,
                          @RequestParam(name="description") String description,
                          @RequestParam(name="tag") String tag,
                          @RequestParam(name="id" , defaultValue = "0") String id,
                          HttpServletRequest request,
                          Model model){
        if(title==null || title==""){
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        model.addAttribute("title", title);
        if(description==null || description==""){
            model.addAttribute("error", "问题描述不能为空");
            return "publish";
        }
        model.addAttribute("description", description);
        if(tag==null || tag==""){
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        model.addAttribute("tag", tag);

        User user = Utils.CheckUserToken(request, userMapper);
        if(user != null){
            request.getSession().setAttribute("user", user);
        }else{
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setId(Integer.parseInt(id));
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        if(question.getId() == 0){
            question.setCreator(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setCommentCount(0);
            question.setLikeCount(0);
            question.setViewCount(0);
        }else{
            question.setGmtModified(System.currentTimeMillis());
        }
        questionService.addQuestionOrUpdateQuestion(question);
        return "redirect:/";
    }
}

