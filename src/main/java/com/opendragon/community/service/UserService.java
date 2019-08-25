package com.opendragon.community.service;

import com.opendragon.community.dto.GithubUser;
import com.opendragon.community.mapper.UserMapper;
import com.opendragon.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author opendragonhuang
 * @version 1.0
 * @date 2019/8/24
 */
@Service
public class UserService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdateUser(User user){
        User dbUser = userMapper.getUserByAccountId(user.getAccountId());
        if(dbUser == null){
            // 插入操作
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insertUser(user);
        }else {
            // 更新操作
            dbUser.setName(user.getName());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setToken(user.getToken());
            userMapper.updateUserByAccountId(dbUser);
        }
    }
}
