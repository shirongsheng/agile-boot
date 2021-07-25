package com.shirs.agileboot.modules.system.mapper;

import com.shirs.agileboot.model.OperationLog;

import java.util.List;

public interface LogMapper {
    int insert(OperationLog operationLog);

    int insertSelective(OperationLog operationLog);

    List<OperationLog> logList(OperationLog operationLog);

    List<OperationLog> allLogList();
}