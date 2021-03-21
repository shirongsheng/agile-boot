package com.shirs.agileboot.modules.system.mapper;

import com.shirs.agileboot.modules.system.entity.UserVo;

import java.util.List;

public interface UserMapper {

    List<UserVo> userList();

    UserVo selectUser(String username);

}
