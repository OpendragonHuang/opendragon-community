package com.opendragon.community.controller;

import com.opendragon.community.dto.QuestionDTO;
import com.opendragon.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;

/**
 * @author opendragonhuang
 * @version 1.0
 * @date 2019/8/24
 */
@Controller
public class QuestionController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id,
                            Model model){
        questionService.incrementViewCount(id);
        QuestionDTO questionDTO = questionService.findDTOById(id);
        model.addAttribute("question", questionDTO);
        return "question";
    }
}
