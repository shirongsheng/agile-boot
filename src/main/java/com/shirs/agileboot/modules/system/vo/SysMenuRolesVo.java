package com.shirs.agileboot.modules.system.vo;

import com.shirs.agileboot.modules.system.entity.SysMenu;
import com.shirs.agileboot.modules.system.entity.SysRole;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.List;

@Data
@Builder
public class SysMenuRolesVo {

    @Tolerate
    public SysMenuRolesVo(){

    }

    private SysMenu sysMenu;

    private List<SysRole> roles;
}
