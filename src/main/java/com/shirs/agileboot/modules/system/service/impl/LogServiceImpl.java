package com.shirs.agileboot.modules.system.service.impl;

import com.shirs.agileboot.model.OperationLog;
import com.shirs.agileboot.modules.system.mapper.LogMapper;
import com.shirs.agileboot.modules.system.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public int insert(OperationLog operationLog) {
        return logMapper.insert(operationLog);
    }

    @Override
    public List<OperationLog> logList(OperationLog operationLog) {
        return logMapper.logList(operationLog);
    }
}
