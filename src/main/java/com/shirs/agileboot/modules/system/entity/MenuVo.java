package com.shirs.agileboot.modules.system.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuVo implements Serializable {

    private Integer menuId;

    private String menuName;

    private String menuUrl;

    private String parentId;

    private String level;

    private String menuStatus;
}
