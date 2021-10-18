package com.shirs.agileboot.modules.system.entity;

import com.shirs.agileboot.common.page.PageRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileVo extends PageRequest {

    private String fileId;

    private String fileName;

    private String files;

    private String createTime;
}
