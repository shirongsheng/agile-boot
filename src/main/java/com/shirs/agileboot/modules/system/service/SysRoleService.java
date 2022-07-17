package com.shirs.agileboot.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shirs.agileboot.modules.system.entity.SysRole;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {

    List<SysRole> getRolesByRoleIds(List<Long> roleIds);

    List<SysRole> getRolesById(Long roleId);
}
