package com.opendragon.community.service;

import com.opendragon.community.dto.PageInformation;
import com.opendragon.community.dto.QuestionDTO;
import com.opendragon.community.mapper.QuestionMapper;
import com.opendragon.community.mapper.UserMapper;
import com.opendragon.community.model.Question;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author opend
 * @Date 2019/7/26
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;


    public List<QuestionDTO> list(){
        List<Question> questions = questionMapper.list();
        ArrayList<QuestionDTO> questionDTOS = new ArrayList<>();

        for(Question question : questions){
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(userMapper.findById(question.getCreator()));
            questionDTOS.add(questionDTO);
        }

        return questionDTOS;
    }

    public PageInformation list(long page, final long pageSize){
        long offset = (page-1)*pageSize;
        List<Question> questions = questionMapper.limitList(offset, pageSize);
        ArrayList<QuestionDTO> questionDTOS = new ArrayList<>();

        for(Question question : questions){
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(userMapper.findById(question.getCreator()));
            questionDTOS.add(questionDTO);
        }

        PageInformation pageInformation = new PageInformation(page, pageSize, questionMapper.count());
        pageInformation.setQuestionDTOs(questionDTOS);

        return pageInformation;
    }


}
