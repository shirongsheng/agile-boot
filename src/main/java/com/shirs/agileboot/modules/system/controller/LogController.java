package com.shirs.agileboot.modules.system.controller;

import com.alibaba.excel.EasyExcel;
import com.shirs.agileboot.common.page.PageResult;
import com.shirs.agileboot.excel.ExcelListener;
import com.shirs.agileboot.model.OperationLog;
import com.shirs.agileboot.modules.system.service.LogService;
import com.shirs.agileboot.common.utils.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
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
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        List<OperationLog> list = logService.list();
        // 这里需要设置不关闭流
        EasyExcel.write(response.getOutputStream(), OperationLog.class)
                .autoCloseStream(Boolean.FALSE)
                .sheet("模板")
                .doWrite(list);
    }

    @GetMapping("/exportTemplateExcel")
    public void exportTemplateExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        // 这里需要设置不关闭流
        EasyExcel.write(response.getOutputStream(), OperationLog.class)
                .autoCloseStream(Boolean.FALSE)
                .sheet("模板")
                .doWrite(new ArrayList());
    }

    @PostMapping("/upload")
    public String uploadFile(MultipartFile file, HttpServletRequest req){

        String filePath = FileUtils.uploadFileToDisk(file);
        EasyExcel.read(filePath,OperationLog.class,new ExcelListener()).sheet().doRead();
        return "上传成功!";

    }
}
