package com.shirs.agileboot.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shirs.agileboot.modules.system.entity.SysRole;
import com.shirs.agileboot.modules.system.mapper.SysRoleMapper;
import com.shirs.agileboot.modules.system.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public List<SysRole> getRolesByRoleIds(List<Long> roleIds) {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SysRole::getId, roleIds);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<SysRole> getRolesById(Long roleId) {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRole::getId, roleId);
        return baseMapper.selectList(wrapper);
    }
}
