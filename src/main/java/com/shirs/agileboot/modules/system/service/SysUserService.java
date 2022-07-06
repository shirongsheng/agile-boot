package com.shirs.agileboot.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shirs.agileboot.modules.system.entity.SysUser;

import java.util.Map;

public interface SysUserService extends IService<SysUser> {

    SysUser selectByUserName(String username);

    Map<String,String> login(SysUser sysUser);

    void logout();
}
