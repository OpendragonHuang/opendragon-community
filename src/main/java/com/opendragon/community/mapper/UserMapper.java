package com.opendragon.community.mapper;

import com.opendragon.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author opend
 * @version 1.0
 * @date 2019/7/25
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user " +
            "(name, account_id, token, gmt_create, gmt_modified)" +
            "values(#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified})")
    void insertUser(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);
}

