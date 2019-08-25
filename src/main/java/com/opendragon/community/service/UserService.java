package com.opendragon.community.service;

import com.opendragon.community.dto.GithubUser;
import com.opendragon.community.mapper.UserMapper;
import com.opendragon.community.model.User;
import com.opendragon.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
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
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> userList = userMapper.selectByExample(userExample);
        if(userList.size() == 0){
            // 插入操作
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            // 更新操作
            User dbUser = userList.get(0);
            dbUser.setName(user.getName());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setToken(user.getToken());
            userExample.clear();
            userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
            userMapper.updateByExampleSelective(dbUser, userExample);
        }
    }
}
