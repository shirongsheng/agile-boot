package com.shirs.agileboot.common.utils;

import com.shirs.agileboot.common.constant.AgileConstant;
import com.shirs.agileboot.modules.system.entity.AlarmVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtils {

    /*
     * 上传文件到磁盘一个目录下
     * */
    public static String uploadFileToDisk(MultipartFile file) {
        SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
        String format = sdf.format(new Date());
        String path = "E:\\agile_boot_file" + format;
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String oldName = file.getOriginalFilename();
        String newName = "shirs" + oldName.substring(oldName.lastIndexOf("."));
        try {
            file.transferTo(new File(folder, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filePath = path + newName;
        return filePath;
    }

    /**
     * 将数据写成csv文件
     *
     * @param filePath 生成的文件路径
     * @param fileName 生成的文件名称
     * @param list     要写入的数据
     */
    public static void writeCsv(String[] fileHeader, String filePath, String fileName, List list) {
        File file = new File(filePath + "\\" + fileName);
        BufferedWriter bw;
        if (file.exists())
            file.delete();
        try {
            file.createNewFile();
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            for (int i = 0; i < fileHeader.length; i++) {
                String field = fileHeader[i];
                bw.write("\"" + field + "\",");
            }
            bw.newLine();
            for (int i = 0; i < list.size(); i++) {
                AlarmVo alarmVo = (AlarmVo) list.get(i);
                bw.write("\"" + alarmVo.getAlarmId() + "\","
                        + "\"" + alarmVo.getAlarmName() + "\","
                        + "\"" + alarmVo.getAlarmTime() + "\","
                        + "\"" + alarmVo.getAlarmType() + "\",");
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将文件压缩成zip
     *
     * @param srcFilePath 要压缩的文件路径
     * @param zipFilePath 生成zip文件的路径
     */
    public static void zip(String srcFilePath, String zipFilePath) {
        ZipOutputStream zos = null;
        InputStream is;
        File srcfile = new File(srcFilePath);
        try {
            byte[] buffer = new byte[2 * AgileConstant.BUFFER_SIZE];
            is = new BufferedInputStream(new FileInputStream(srcfile));
            zos = new ZipOutputStream(new FileOutputStream(new File(zipFilePath)));
            zos.putNextEntry(new ZipEntry(srcfile.getName()));
            int len = 0;
            while ((len = is.read(buffer)) != -1)
                zos.write(buffer, 0, len);
            zos.closeEntry();
            zos.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将文件转成byte数组
     *
     * @param filePath 文件地址
     * @return 比特数组
     */
    public static byte[] fileConvertToByteArray(String filePath) {
        byte[] byteData = null;
        File file = new File(filePath);
        FileInputStream fs;
        ByteArrayOutputStream bos;
        try {
            fs = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[AgileConstant.BUFFER_SIZE];
            int len = 0;
            while ((len = fs.read(buffer)) != -1)
                bos.write(buffer, 0, len);
            byteData = bos.toByteArray();
            fs.close();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteData;
    }
}
