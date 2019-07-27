package com.opendragon.community.mapper;

import com.opendragon.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author opend
 * @version 1.0
 * @date 2019/7/26
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into question" +
            "(title, description, gmt_create, gmt_modified, creator, tag)" +
            "values(#{title},#{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    void insertQueston(Question question);

    @Select("select title, description, creator, comment_count, view_count, gmt_modified from question")
    List<Question> list();
}

