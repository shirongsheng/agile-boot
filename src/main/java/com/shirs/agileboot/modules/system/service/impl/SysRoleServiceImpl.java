package com.shirs.agileboot.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shirs.agileboot.modules.system.entity.RoleCreateVo;
import com.shirs.agileboot.modules.system.entity.SysRole;
import com.shirs.agileboot.modules.system.entity.SysUser;
import com.shirs.agileboot.modules.system.mapper.SysRoleMapper;
import com.shirs.agileboot.modules.system.service.SysRoleService;
import com.shirs.agileboot.modules.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;


@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysUserService userService;

    @Override
    public List<SysRole> getRolesByRoleIds(List<Long> roleIds) {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();

        if (CollectionUtils.isEmpty(roleIds)) {
            wrapper.apply("0=1");
        } else {
            wrapper.in(SysRole::getId, roleIds);
        }
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<SysRole> getRolesById(Long roleId) {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRole::getId, roleId);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public int create(RoleCreateVo roleCreateVo) {
        SysUser user = userService.getUser();
        long userId = user.getId();

        SysRole role = SysRole.builder()
                .name(roleCreateVo.getName())
                .create_by(userId)
                .create_time(new Date())
                .update_by(userId)
                .update_time(new Date())
                .remark(roleCreateVo.getRemark())
                .build();

        return baseMapper.insert(role);
    }
}
