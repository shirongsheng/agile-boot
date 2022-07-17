package com.shirs.agileboot.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shirs.agileboot.modules.system.entity.SysUserRole;

import java.util.List;

public interface SysUserRoleService extends IService<SysUserRole> {

    List<SysUserRole> getUserRoleByUserId(Long userId);
}
