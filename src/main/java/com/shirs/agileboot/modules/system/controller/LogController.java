package com.shirs.agileboot.modules.system.controller;

import com.alibaba.excel.EasyExcel;
import com.shirs.agileboot.common.page.PageResult;
import com.shirs.agileboot.model.OperationLog;
import com.shirs.agileboot.modules.system.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

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

    @GetMapping("/exportExcel")
    public void download(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        List<OperationLog> list = logService.list(new OperationLog());
        EasyExcel.write(response.getOutputStream(), OperationLog.class).sheet("模板").doWrite(list);
    }
}
