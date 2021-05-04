package com.shirs.agileboot.modules.system.service.impl;

import com.shirs.agileboot.modules.system.entity.User;
import com.shirs.agileboot.modules.system.entity.UserVo;
import com.shirs.agileboot.modules.system.mapper.UserMapper;
import com.shirs.agileboot.modules.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserVo> userList(User user) {
        return userMapper.userList(user);
    }

    @Override
    public UserVo selectUser(String username) {
        return userMapper.selectUser(username);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }
}
