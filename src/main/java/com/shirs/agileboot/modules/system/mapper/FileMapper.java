package com.shirs.agileboot.modules.system.mapper;

import com.shirs.agileboot.modules.system.entity.FileVo;

import java.util.List;

public interface FileMapper {
    List<FileVo> fileList(FileVo fileVo);

    FileVo selectByFileId(String fileId);

    int insert(FileVo fileVo);
}