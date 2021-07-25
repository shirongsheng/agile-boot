package com.shirs.agileboot.modules.system.mapper;

import com.shirs.agileboot.test.FileBean;
import com.shirs.agileboot.modules.system.entity.User;
import com.shirs.agileboot.modules.system.entity.UserVo;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<UserVo> userList(User user);

    UserVo selectUser(String username);

    int deleteBatch(List ids);

    /**
     * 分页查询用户
     * @return
     */
    List<UserVo> selectPage(User user);

    int insertFile(FileBean fileBean);

    FileBean selectFile();
}