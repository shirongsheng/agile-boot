package com.shirs.agileboot.modules.system.controller;

import com.shirs.agileboot.common.page.PageResult;
import com.shirs.agileboot.model.OperationLog;
import com.shirs.agileboot.modules.system.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@Api(tags = "日志")
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping("/list")
    @ApiOperation(value = "日志列表",notes = "日志列表")
    public PageResult queryList(@RequestBody OperationLog operationLog){
        return logService.logList(operationLog);
    }
}
