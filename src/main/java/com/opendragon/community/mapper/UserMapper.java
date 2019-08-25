package com.opendragon.community.mapper;

import com.opendragon.community.model.User;
import org.apache.ibatis.annotations.*;

/**
 * @author opend
 * @version 1.0
 * @date 2019/7/25
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user " +
            "(name, account_id, token, gmt_create, gmt_modified, avatar_url)" +
            "values(#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    void insertUser(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id=#{id}")
    User findById(@Param("id") int id);

    @Select("select * from user where account_id=#{accountId}")
    User getUserByAccountId(@Param("accountId") String accountId);

    @Update("update user set name = #{name}, avatar_url = #{avatarUrl}, token=#{token}, gmt_modified=#{gmtModified} where account_id = #{accountId}")
    void updateUserByAccountId(User user);
}

