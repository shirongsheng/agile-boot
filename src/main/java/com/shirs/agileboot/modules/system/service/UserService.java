package com.shirs.agileboot.modules.system.service;

import com.shirs.agileboot.modules.system.entity.UserVo;

import java.util.List;

public interface UserService {

    List<UserVo> userList();

    UserVo selectUser(String username);

}
