package com.shirs.agileboot.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.shirs.agileboot.common.page.PageRequest;
import lombok.Data;

import java.util.Date;

@Data
public class OperationLog extends PageRequest {
    private String id;

    @ExcelProperty("创建时间")
    private Date createTime;
    /**
     * 日志等级
     */
    @ExcelProperty("日志等级")
    private Integer level;
    /**
     * 被操作的对象
     */
    @ExcelProperty("操作对象")
    private String operationUnit;
    /**
     * 方法名
     */
    @ExcelProperty("方法名")
    private String method;
    /**
     * 参数
     */
    @ExcelProperty("参数")
    private String args;
    /**
     * 操作人id
     */
    @ExcelProperty("操作人id")
    private String userId;
    /**
     * 操作人
     */
    @ExcelProperty("操作人")
    private String userName;
    /**
     * 日志描述
     */
    @ExcelProperty("日志描述")
    private String description;
    /**
     * 操作类型
     */
    @ExcelProperty("操作类型")
    private String operationType;
    /**
     * 方法运行时间
     */
    @ExcelProperty("方法运行时间")
    private Long runTime;
    /**
     * 方法返回值
     */
    @ExcelProperty("方法返回值")
    private String returnValue;
}
