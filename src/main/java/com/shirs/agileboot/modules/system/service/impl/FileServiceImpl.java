package com.shirs.agileboot.modules.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shirs.agileboot.common.constant.AgileConstant;
import com.shirs.agileboot.common.page.PageResult;
import com.shirs.agileboot.common.utils.DateUtils;
import com.shirs.agileboot.common.utils.FileUtils;
import com.shirs.agileboot.common.utils.PageUtils;
import com.shirs.agileboot.modules.system.entity.AlarmVo;
import com.shirs.agileboot.modules.system.entity.FileVo;
import com.shirs.agileboot.modules.system.mapper.FileMapper;
import com.shirs.agileboot.modules.system.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public PageResult fileList(FileVo fileVo) {
        int pageNum = fileVo.getPageNum();
        int pageSize = fileVo.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<FileVo> fileVos = fileMapper.fileList(fileVo);
        PageInfo<FileVo> filePageInfo = new PageInfo<>(fileVos);
        PageResult pageResult = PageUtils.getPageResult(fileVo, filePageInfo);
        return pageResult;
    }

    @Override
    public int createFile(JSONObject content) {
        //组装数据
        List<AlarmVo> alarmVos = new ArrayList<>();
        AlarmVo alarmVo = AlarmVo.builder().alarmId(content.getInteger("alarmId"))
                .alarmName(content.getString("alarmName"))
                .alarmTime(content.getString("alarmTime"))
                .alarmType(content.getString("alarmType")).build();
        alarmVos.add(alarmVo);
        //写成csv
        FileUtils.writeCsv(AgileConstant.fileHeader, AgileConstant.filePath, AgileConstant.csvFileName, alarmVos);
        //压缩成zip
        FileUtils.zip(AgileConstant.filePath + "\\" + AgileConstant.csvFileName
                , AgileConstant.filePath + "\\" + AgileConstant.zipFileName);
        //转码后入库
        byte[] bytes = FileUtils.fileConvertToByteArray(AgileConstant.filePath + "\\" + AgileConstant.zipFileName);
        String fileBase64String = Base64.getEncoder().encodeToString(bytes);
        String nowDate = DateUtils.getFormatDateTime(new Date());
        FileVo fileVo = FileVo.builder().fileId(UUID.randomUUID().toString())
                .files(fileBase64String)
                .createTime(nowDate)
                .fileName(AgileConstant.zipFileName).build();
        return fileMapper.insert(fileVo);
    }

    @Override
    public ResponseEntity<byte[]> download(String fileId) {
        FileVo fileVo = fileMapper.selectByFileId(fileId);
        String files = fileVo.getFiles();
        byte[] fileBytes = Base64.getDecoder().decode(files);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + fileVo.getFileName() + ".zip");
        return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
    }
}
