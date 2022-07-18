package com.shirs.agileboot.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class SysRole {

    @Tolerate
    public SysRole() {

    }

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String status;

    private Long create_by;

    private Date create_time;

    private Long update_by;

    private Date update_time;

    private Integer del_flag;

    private String remark;
}
