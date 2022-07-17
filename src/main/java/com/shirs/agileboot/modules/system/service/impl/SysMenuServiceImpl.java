package com.shirs.agileboot.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shirs.agileboot.modules.system.entity.SysMenu;
import com.shirs.agileboot.modules.system.entity.SysRoleMenu;
import com.shirs.agileboot.modules.system.entity.SysRole;
import com.shirs.agileboot.modules.system.mapper.SysMenuMapper;
import com.shirs.agileboot.modules.system.service.SysMenuRoleService;
import com.shirs.agileboot.modules.system.service.SysMenuService;
import com.shirs.agileboot.modules.system.service.SysRoleService;
import com.shirs.agileboot.modules.system.vo.SysMenuRolesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuRoleService menuRoleService;

    @Autowired
    private SysRoleService roleService;

    @Override
    public List<SysMenuRolesVo> getMenuRoles() {
        List<SysMenu> sysMenus = baseMapper.selectList(null);
        List<SysMenuRolesVo> list = new ArrayList<>();
        for (SysMenu menu : sysMenus) {
            List<SysRoleMenu> menuRole = menuRoleService.getMenuRoleByMenuId(menu.getId());
            List<Long> roleIds = menuRole.stream()
                    .map(SysRoleMenu::getRoleId)
                    .collect(Collectors.toList());
            List<SysRole> roles = roleService.getRolesByRoleIds(roleIds);
            SysMenuRolesVo sysMenuRolesVo = SysMenuRolesVo.builder()
                    .sysMenu(menu)
                    .roles(roles)
                    .build();
            list.add(sysMenuRolesVo);
        }
        return list;
    }
}
