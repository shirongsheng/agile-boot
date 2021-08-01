package com.shirs.agileboot.modules.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shirs.agileboot.common.page.PageResult;
import com.shirs.agileboot.common.utils.PageUtils;
import com.shirs.agileboot.modules.system.entity.User;
import com.shirs.agileboot.modules.system.entity.UserVo;
import com.shirs.agileboot.modules.system.mapper.UserMapper;
import com.shirs.agileboot.modules.system.service.UserService;
import com.shirs.agileboot.websocket.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WebSocketService webSocketService;

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
        userMapper.insert(user);
        //新增用户后，发送消息到前端
        try {
            webSocketService.sendMessage("新增用户成功!");
            //webSocketService.sendMessage("1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public int deleteBatch(List ids) {
        return userMapper.deleteBatch(ids);
    }

    @Override
    public PageResult findPage(User user) {
        int pageNum = user.getPageNum();
        int pageSize = user.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<UserVo> userVos = userMapper.selectPage(user);
        PageInfo<UserVo> userVoPageInfo = new PageInfo<>(userVos);
        PageResult pageResult = PageUtils.getPageResult(user, userVoPageInfo);
        return pageResult;
    }
}
