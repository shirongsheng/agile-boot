package com.shirs.agileboot.modules.system.service;

import com.shirs.agileboot.common.page.PageResult;
import com.shirs.agileboot.model.OperationLog;

import java.util.List;

public interface LogService {

    int insert(OperationLog operationLog);

    PageResult logList(OperationLog operationLog);

    List<OperationLog> list();
}
