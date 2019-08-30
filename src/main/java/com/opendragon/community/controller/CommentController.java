package com.opendragon.community.controller;

import com.opendragon.community.dto.CommentRequestDTO;
import com.opendragon.community.dto.CommentResponseDTO;
import com.opendragon.community.dto.ResultDTO;
import com.opendragon.community.enums.CommentTypeEnum;
import com.opendragon.community.exception.CustomizeErrorCode;
import com.opendragon.community.exception.ICustomizeErrorCode;
import com.opendragon.community.model.Comment;
import com.opendragon.community.model.User;
import com.opendragon.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * @author opendragonhuang
 * @version 1.0
 * @date 2019/8/26
 */
@Controller
public class CommentController implements Serializable {
    private static final long serialVersionUID = 1L;
    @Autowired
    private CommentService commentService;

    @RequestMapping("/comment")
    @ResponseBody
    public Object addComment(@RequestBody CommentRequestDTO commentDTO, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setComment(commentDTO.getComment());
        comment.setCommentator(user.getId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(0);
        comment.setSubCommentCount(0);
        commentService.insert(comment);

        return ResultDTO.ok(CustomizeErrorCode.OK);
    }

    @GetMapping("/comment/{id}")
    @ResponseBody
    public Object getSubComment(@PathVariable(name = "id") Integer id){
        List<CommentResponseDTO> commentResponseDTOList = commentService.findByParentId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.ok(CustomizeErrorCode.OK, commentResponseDTOList);
    }
}
