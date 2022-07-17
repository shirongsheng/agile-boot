package com.shirs.agileboot.modules.system.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class SysMenu {

    @Tolerate
    public SysMenu(){

    }

    private Long id;

    private String menuName;

    private String path;

    private String component;

    private String visible;

    private String status;

    private String perms;

    private String icon;

    private Long create_by;

    private Date create_time;

    private Long update_by;

    private Date update_time;

    private Integer del_flag;

    private String remark;
}
