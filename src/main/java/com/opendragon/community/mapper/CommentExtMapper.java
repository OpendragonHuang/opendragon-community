package com.opendragon.community.mapper;

import com.opendragon.community.model.Comment;
import com.opendragon.community.model.CommentExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExtMapper {
    int incSubCommentCount(Comment comment);
}