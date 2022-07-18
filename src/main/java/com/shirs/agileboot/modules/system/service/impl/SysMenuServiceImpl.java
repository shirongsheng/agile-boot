package com.shirs.agileboot.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shirs.agileboot.modules.system.entity.*;
import com.shirs.agileboot.modules.system.mapper.SysMenuMapper;
import com.shirs.agileboot.modules.system.service.SysMenuRoleService;
import com.shirs.agileboot.modules.system.service.SysMenuService;
import com.shirs.agileboot.modules.system.service.SysRoleService;
import com.shirs.agileboot.modules.system.service.SysUserService;
import com.shirs.agileboot.modules.system.vo.SysMenuRolesVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysUserService userService;

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

    @Override
    public void create(MenuCreateVo menuCreateVo) {

        Integer nodeType = menuCreateVo.getNodeType();

        SysUser user = userService.getUser();
        long userId = user.getId();

        SysMenu menu = SysMenu.builder()
                .menuName(menuCreateVo.getMenuName())
                .nodeType(nodeType)
                .sort(menuCreateVo.getSort())
                .icon(menuCreateVo.getIcon())
                .create_by(userId)
                .create_time(new Date())
                .update_by(userId)
                .update_time(new Date())
                .build();

        if (nodeType == 2) {
            //TODO  暂时这么写，后面优化  最上级菜单只有系统管理
            menu.setParentId(0l);
            menu.setLevel(1);
            //页面的path为null
            menu.setPath(null);
        } else {
            SysMenu parentMenu = baseMapper.selectById(menu.getParentId());
            if (null == parentMenu) {
                log.error("未查询到对应的父节点");
                menu.setLevel(parentMenu.getLevel().intValue() + 1);
                if (StringUtils.isNoneEmpty(parentMenu.getPath())) {
                    menu.setPath(parentMenu.getPath() + "," + parentMenu.getId());
                } else {
                    menu.setPath(parentMenu.getId().toString());
                }
            }
        }

        baseMapper.insert(menu);
    }

    @Override
    public List<SysMenu> menuList() {
        return baseMapper.selectList(null);
    }

    @Override
    public List<MenuVo> queryMenuTree() {
        List<SysMenu> allMenu = menuList();
        return toMenuVo(allMenu, 0l);
    }

    private List<MenuVo> toMenuVo(List<SysMenu> allMenu, Long parentId) {
        List<MenuVo> menuVos = new ArrayList<>();
        for (SysMenu menu : allMenu) {
            if (parentId.longValue() == menu.getParentId().longValue()) {
                MenuVo menuVo = new MenuVo();
                BeanUtils.copyProperties(menu, menuVo);
                List<MenuVo> childMenu = toMenuVo(allMenu, menu.getId());
                if (!CollectionUtils.isEmpty(childMenu))
                    menuVo.setChildMenu(childMenu);
                menuVos.add(menuVo);
            }
        }
        return menuVos;
    }
}
