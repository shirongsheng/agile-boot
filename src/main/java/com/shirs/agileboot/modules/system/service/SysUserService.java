package com.shirs.agileboot.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shirs.agileboot.modules.system.entity.SysUser;
import com.shirs.agileboot.modules.system.vo.UserQueryVo;

import java.util.List;
import java.util.Map;

public interface SysUserService extends IService<SysUser> {

    SysUser selectByUserName(String username);

    Map<String,String> login(SysUser sysUser);

    void logout();

    List<SysUser> userList();

    IPage<SysUser> findPage(UserQueryVo userQueryVo);

    List<String> getRolesById(Long userId);
}
