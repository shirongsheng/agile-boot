package com.shirs.agileboot.modules.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shirs.agileboot.common.page.PageResult;
import com.shirs.agileboot.common.utils.PageUtils;
import com.shirs.agileboot.model.OperationLog;
import com.shirs.agileboot.modules.system.entity.UserVo;
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
    public PageResult logList(OperationLog operationLog) {
        int pageNum = operationLog.getPageNum();
        int pageSize = operationLog.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<OperationLog> operationLogs = logMapper.logList(operationLog);
        PageInfo<OperationLog> operationLogPageInfo = new PageInfo<>(operationLogs);
        PageResult pageResult = PageUtils.getPageResult(operationLog, operationLogPageInfo);
        return pageResult;
    }

    @Override
    public List<OperationLog> list(OperationLog operationLog) {
        return logMapper.logList(operationLog);
    }
}
