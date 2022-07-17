package com.shirs.agileboot.modules.system.securityService;

import com.shirs.agileboot.modules.system.service.SysMenuService;
import com.shirs.agileboot.modules.system.vo.SysMenuRolesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class CustomSecurityMetadataSource
        implements FilterInvocationSecurityMetadataSource {
    @Autowired
    SysMenuService menuService;
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {
        String requestURI =
                ((FilterInvocation) object).getRequest().getRequestURI();
        List<SysMenuRolesVo> allMenu = menuService.getMenuRoles();
        for (SysMenuRolesVo menu : allMenu) {
            if (antPathMatcher.match(menu.getSysMenu().getPath(), requestURI)) {
                String[] roles = menu.getRoles().stream()
                        .map(r -> r.getName()).toArray(String[]::new);
                return SecurityConfig.createList(roles);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
