package com.shirs.agileboot.modules.system.service;

import com.alibaba.fastjson.JSONObject;
import com.shirs.agileboot.common.page.PageResult;
import com.shirs.agileboot.modules.system.entity.FileVo;
import org.springframework.http.ResponseEntity;

public interface FileService {

    PageResult fileList(FileVo fileVo);

    int createFile(JSONObject content);

    ResponseEntity<byte[]> download(String fileId);
}
