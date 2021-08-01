package com.shirs.agileboot.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils {

    /*
    * 上传文件到磁盘一个目录下
    * */
    public static String uploadFileToDisk(MultipartFile file) {
        SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
        String format = sdf.format(new Date());
        String path = "E:\\agile_boot_file"+format;
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String oldName = file.getOriginalFilename();
        String newName = "shirs"  + oldName.substring(oldName.lastIndexOf("."));
        try {
            file.transferTo(new File(folder, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filePath = path + newName;
        return filePath;
    }
}
