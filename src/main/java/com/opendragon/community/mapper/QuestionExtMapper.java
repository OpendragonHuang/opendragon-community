package com.opendragon.community.mapper;

import com.opendragon.community.model.Question;

import java.util.List;

/**
 * @author opendragonhuang
 * @version 1.0
 * @date 2019/8/26
 */
public interface QuestionExtMapper {
    int incCommentCount(Question question);
    List<Question> selectRelated(Question question);
}
