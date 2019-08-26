package com.opendragon.community.service;

import com.opendragon.community.enums.CommentTypeEnum;
import com.opendragon.community.exception.CustomizeErrorCode;
import com.opendragon.community.exception.CustomizeException;
import com.opendragon.community.mapper.CommentMapper;
import com.opendragon.community.mapper.QuestionExtMapper;
import com.opendragon.community.mapper.QuestionMapper;
import com.opendragon.community.model.Comment;
import com.opendragon.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @author opendragonhuang
 * @version 1.0
 * @date 2019/8/26
 */
@Service
public class CommentService implements Serializable {
    private static final long serialVersionUID = 1L;
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private CommentMapper commentMapper;


    @Transactional
    public int insert(Comment comment){
        if(comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_COMMENT_NOT_FIND);
        }

        if(comment.getType() == null || !CommentTypeEnum.isExists(comment.getType())){
            throw  new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WORN);
        }

        if(comment.getType() == CommentTypeEnum.QUESTION.getType()){
            // 回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId().intValue());
            if(question == null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FIND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);

        }else{
            // 回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FIND);
            }
            commentMapper.insert(comment);
        }
        return 0;
    }
}
