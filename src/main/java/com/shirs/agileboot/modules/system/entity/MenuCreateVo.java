package com.shirs.agileboot.modules.system.entity;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class MenuCreateVo {

    @Tolerate
    public MenuCreateVo(){

    }

    private Long parentId;

    private Integer nodeType;

    private String menuName;

    private String path;

    private Integer sort;

    private String icon;
}
