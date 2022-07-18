package com.shirs.agileboot.modules.system.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class MenuVo implements Serializable {

    @Tolerate
    public MenuVo(){

    }

    private String menuName;

    private Long parentId;

    private Integer nodeType;

    private String icon;

    /**
     * 排序号
     */
    private Integer sort;

    private String linkUrl;

    private Integer level;

    private String path;

    private Long create_by;

    private Date create_time;

    private Long update_by;

    private Date update_time;

    private Integer isDelete;

    List<MenuVo> childMenu;
}
