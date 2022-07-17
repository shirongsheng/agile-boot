package com.shirs.agileboot.modules.system.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class SysRoleMenu {

    @Tolerate
    public SysRoleMenu(){

    }

    private Long menuId;

    private Long roleId;
}
