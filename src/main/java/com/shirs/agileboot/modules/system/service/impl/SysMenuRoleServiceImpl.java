package com.shirs.agileboot.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shirs.agileboot.modules.system.entity.SysRoleMenu;
import com.shirs.agileboot.modules.system.mapper.SysMenuRoleMapper;
import com.shirs.agileboot.modules.system.service.SysMenuRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuRoleServiceImpl extends ServiceImpl<SysMenuRoleMapper, SysRoleMenu> implements SysMenuRoleService {

    @Override
    public List<SysRoleMenu> getMenuRoleByMenuId(Long menuId) {
        LambdaQueryWrapper<SysRoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleMenu::getMenuId, menuId);
        return baseMapper.selectList(wrapper);
    }
}
