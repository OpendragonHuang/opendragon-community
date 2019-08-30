package com.opendragon.community.service;

import com.opendragon.community.dto.CommentResponseDTO;
import com.opendragon.community.enums.CommentTypeEnum;
import com.opendragon.community.exception.CustomizeErrorCode;
import com.opendragon.community.exception.CustomizeException;
import com.opendragon.community.mapper.*;
import com.opendragon.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private CommentExtMapper commentExtMapper;

    @Autowired
    private UserMapper userMapper;


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
            dbComment.setSubCommentCount(1);
            commentExtMapper.incSubCommentCount(dbComment);
            commentMapper.insert(comment);
        }
        return 0;
    }

    public List<CommentResponseDTO> findByParentId(Integer id, CommentTypeEnum typeEnum) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id.longValue())
                .andTypeEqualTo(typeEnum.getType());
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        List<CommentResponseDTO> commentResponseDTOS = new ArrayList<>();
        UserExample userExample = new UserExample();
        for (Comment comment : comments) {
            CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
            BeanUtils.copyProperties(comment, commentResponseDTO);
            userExample.clear();
            userExample.createCriteria().andIdEqualTo(comment.getCommentator());
            List<User> userList = userMapper.selectByExample(userExample);
            if(userList.size() > 0){
                commentResponseDTO.setUser(userList.get(0));
            }
            commentResponseDTOS.add(commentResponseDTO);
        }
        return commentResponseDTOS;
    }
}
