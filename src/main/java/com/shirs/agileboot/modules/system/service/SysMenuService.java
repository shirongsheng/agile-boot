package com.shirs.agileboot.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shirs.agileboot.modules.system.entity.MenuCreateVo;
import com.shirs.agileboot.modules.system.entity.MenuVo;
import com.shirs.agileboot.modules.system.entity.SysMenu;
import com.shirs.agileboot.modules.system.vo.SysMenuRolesVo;

import java.util.List;

public interface SysMenuService extends IService<SysMenu> {

    List<SysMenuRolesVo> getMenuRoles();

    void create(MenuCreateVo menuCreateVo);

    List<SysMenu> menuList();

    List<MenuVo> queryMenuTree();
}
