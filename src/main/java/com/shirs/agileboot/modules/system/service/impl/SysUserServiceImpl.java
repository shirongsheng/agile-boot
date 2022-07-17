package com.shirs.agileboot.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shirs.agileboot.common.utils.JwtUtils;
import com.shirs.agileboot.config.RedisCache;
import com.shirs.agileboot.exception.BizException;
import com.shirs.agileboot.exception.CommonEnum;
import com.shirs.agileboot.modules.system.entity.SysRole;
import com.shirs.agileboot.modules.system.entity.SysUser;
import com.shirs.agileboot.modules.system.entity.SysUserRole;
import com.shirs.agileboot.modules.system.mapper.SysUserMapper;
import com.shirs.agileboot.modules.system.service.SysRoleService;
import com.shirs.agileboot.modules.system.service.SysUserRoleService;
import com.shirs.agileboot.modules.system.service.SysUserService;
import com.shirs.agileboot.modules.system.vo.UserQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysUserRoleService userRoleService;

    @Autowired
    private SysRoleService roleService;

    @Override
    public SysUser selectByUserName(String username) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, username);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public Map<String, String> login(SysUser sysUser) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sysUser.getUsername(), sysUser.getPassword());
        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            throw new BizException(CommonEnum.BAD_CREDENTIALS);
        }

        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }

        SysUser user = (SysUser) authenticate.getPrincipal();
        long userId = user.getId();
        String jwt = JwtUtils.createJWT(String.valueOf(userId));

        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);

        redisCache.setCacheObject("login:" + userId, user);
        return map;
    }

    @Override
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser loginUser = (SysUser) authentication.getPrincipal();
        Long userid = loginUser.getId();
        redisCache.deleteObject("login:" + userid);
    }

    @Override
    public List<SysUser> userList() {
        return baseMapper.selectList(null);
    }

    @Override
    public IPage<SysUser> findPage(UserQueryVo userQueryVo) {

        int pageNum = userQueryVo.getPageNum();
        int pageSize = userQueryVo.getPageSize();
        Page<SysUser> page = new Page<>(pageNum, pageSize);
        IPage<SysUser> sysUserIPage = baseMapper.selectPage(page, null);
        return sysUserIPage;
    }

    @Override
    public List<String> getRolesById(Long userId) {
        List<SysUserRole> userRoles = userRoleService.getUserRoleByUserId(userId);

        List<Long> roleIds = userRoles.stream()
                .map(SysUserRole::getRoleId)
                .collect(Collectors.toList());

        List<SysRole> roles = roleService.getRolesByRoleIds(roleIds);

        List<String> roleNames = roles.stream()
                .map(SysRole::getName)
                .collect(Collectors.toList());

        return roleNames;
    }
}
