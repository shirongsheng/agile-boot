package com.shirs.agileboot.modules.system.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class SysUserRole {

    @Tolerate
    public SysUserRole(){

    }

    private Long userId;

    private Long roleId;
}
