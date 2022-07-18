package com.shirs.agileboot.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String menuName;

    /**
     * 父菜单id
     */
    private Long parentId;

    /**
     * 1文件夹 2页面 3按钮
     */
    private Integer nodeType;

    private String icon;

    /**
     * 排序号
     */
    private Integer sort;


    /**
     * 页面对应的地址
     */
    private String linkUrl;

    /**
     * 层次
     */
    private Integer level;

    /**
     * 树id的路径，整个层次上的路径id
     */
    private String path;

    private Long create_by;

    private Date create_time;

    private Long update_by;

    private Date update_time;

    private Integer isDelete;
}
