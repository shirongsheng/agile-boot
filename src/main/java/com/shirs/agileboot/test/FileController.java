package com.shirs.agileboot.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
@Api(tags = "文件")
public class FileController {

    @Autowired
    private FileToDb fileToDb;

    @GetMapping("/getFile")
    @ApiOperation(value = "获取文件",notes = "获取文件")
    public ResponseEntity<byte[]> queryList(){
        return fileToDb.byteToFile();
    }
}
