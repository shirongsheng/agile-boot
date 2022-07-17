package com.shirs.agileboot.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shirs.agileboot.modules.system.entity.SysRoleMenu;

import java.util.List;

public interface SysMenuRoleService extends IService<SysRoleMenu> {

    List<SysRoleMenu> getMenuRoleByMenuId(Long menuId);
}
