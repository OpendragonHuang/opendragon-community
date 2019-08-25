package com.opendragon.community.mapper;

import com.opendragon.community.model.Question;
import org.apache.ibatis.annotations.*;

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

    @Select("select * from question order by gmt_modified desc")
    List<Question> list();

    @Select("select * from question order by gmt_modified desc limit #{offset}, #{rows}")
    List<Question> limitList(@Param("offset") long offset,@Param("rows") long rows);

    @Select("select count(1) from question")
    long count();

    @Select("select count(1) from question where creator=#{creatorId}")
    long sameCreatorQuestionCount(@Param("creatorId") Integer creatorId);

    @Select("select * from question where creator=#{creatorId} limit #{offset}, #{rows}")
    List<Question> listByCreator(@Param("creatorId") Integer creatorId,@Param("offset") long offset,@Param("rows") long rows);


    @Select("select * from question where id = #{id}")
    Question getQuestionById(@Param("id") Integer id);

    @Update("update question set title = #{title}, description=#{description}, tag=#{tag} where id = #{id}")
    void updateQuestion(Question question);
}

