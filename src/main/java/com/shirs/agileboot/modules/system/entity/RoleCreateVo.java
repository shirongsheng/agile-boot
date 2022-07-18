package com.shirs.agileboot.modules.system.entity;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class RoleCreateVo {

    @Tolerate
    public RoleCreateVo(){

    }

    private String name;

    private String remark;
}
