package com.shirs.agileboot.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shirs.agileboot.modules.system.entity.SysUserRole;
import com.shirs.agileboot.modules.system.mapper.SysUserRoleMapper;
import com.shirs.agileboot.modules.system.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public List<SysUserRole> getUserRoleByUserId(Long userId) {
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId, userId);
        return baseMapper.selectList(wrapper);
    }
}
