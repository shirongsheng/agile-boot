package com.shirs.agileboot.modules.system.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class SysRole {

    @Tolerate
    public SysRole(){

    }

    private Long id;

    private String name;

    private String roleKey;

    private String status;

    private Long create_by;

    private Date create_time;

    private Long update_by;

    private Date update_time;

    private Integer del_flag;

    private String remark;
}
