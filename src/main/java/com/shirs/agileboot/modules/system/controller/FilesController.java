package com.shirs.agileboot.modules.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.shirs.agileboot.annotation.OperationLogDetail;
import com.shirs.agileboot.common.page.PageResult;
import com.shirs.agileboot.enums.OperationType;
import com.shirs.agileboot.enums.OperationUnit;
import com.shirs.agileboot.modules.system.entity.FileVo;
import com.shirs.agileboot.modules.system.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/file")
@Api(tags = "文件")
public class FilesController {

    @Autowired
    private FileService fileService;

    @PostMapping("/list")
    @ApiOperation(value = "文件列表", notes = "文件列表")
    public PageResult queryList(@RequestBody FileVo fileVo) {
        return fileService.fileList(fileVo);
    }

    /**
     * 生成告警数据
     */
    @PostMapping("/createFile")
    @ApiOperation(value = "生成告警文件", notes = "生成告警文件")
    @OperationLogDetail(detail = "生成告警文件", level = 3, operationUnit = OperationUnit.ALARMFILE, operationType = OperationType.INSERT)
    public int registerUser(@RequestBody JSONObject content) {
        return fileService.createFile(content);
    }

    /**
     * 文件下载
     */
    @RequestMapping(value = "/download/{fileId}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    @ApiOperation(value = "文件下载", notes = "文件下载")
    @OperationLogDetail(detail = "文件下载", level = 3, operationUnit = OperationUnit.DOWNLOAD, operationType = OperationType.DOWNLOAD)
    public ResponseEntity<byte[]> download(@PathVariable String fileId) {
        return fileService.download(fileId);
    }
}
