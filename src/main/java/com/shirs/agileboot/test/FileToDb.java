package com.shirs.agileboot.test;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.shirs.agileboot.modules.system.entity.User;
import com.shirs.agileboot.modules.system.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileToDb {

    @Autowired
    private UserMapper userMapper;

    @PostConstruct
    public void simpleWrite() throws Exception {
        //写入数据到文件并写到磁盘
        //writeFile();

        //存储文件到数据库
        //saveToDb();

        //从数据库读取文件
        byteToFile();


    }

    /*
    * 写入数据到文件并写到磁盘
    * */

    public void writeFile(){
        try {


            // 文件输出位置
            String outPath = "E:\\agile_boot_file\\2021\\07\\25\\shirs1111111.xlsx";
            ExcelWriter excelWriter = EasyExcelFactory.getWriter(new FileOutputStream(outPath));
            // 表单
            Sheet sheet = new Sheet(1, 0);
            sheet.setSheetName("第一个Sheet");
            // 创建一个表格
            Table table = new Table(1);
            // 动态添加 表头 headList --> 所有表头行集合
            List<List<String>> headList = new ArrayList<List<String>>();
            // 第 n 行 的表头
            List<String> headTitle1 = new ArrayList<String>();
            List<String> headTitle2 = new ArrayList<String>();
            List<String> headTitle3 = new ArrayList<String>();
            List<String> headTitle4 = new ArrayList<String>();
            List<String> headTitle5 = new ArrayList<String>();
            headTitle1.add("id");
            headTitle2.add("name");
            headTitle3.add("age");
            headTitle4.add("sex");
            headTitle5.add("password");

            headList.add(headTitle1);
            headList.add(headTitle2);
            headList.add(headTitle3);
            headList.add(headTitle4);
            headList.add(headTitle5);
            table.setHead(headList);

            ExcelWriter excelWriter1 = excelWriter.write1(getList(), sheet, table);
            // 记得 释放资源
            excelWriter.finish();
            System.out.println("ok");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List getList() {
        User user = new User();
        user.setId(1);
        user.setAge(24);
        user.setName("孙大圣");
        user.setPassword("你不配");
        user.setSex("公的");
        List<User> list = new ArrayList<>();
        list.add(user);

        // 所有行的集合
        List<List<Object>> res = new ArrayList<List<Object>>();

        for (User user1 : list) {
            List<Object> row = new ArrayList<Object>();
            row.add(user1.getId());
            row.add(user1.getName());
            row.add(user1.getAge());
            row.add(user1.getSex());
            row.add(user1.getPassword());
            res.add(row);
        }

        return res;
    }

    public void saveToDb() {
        byte[] bytes = readFile();
        FileBean fileBean = new FileBean();
        fileBean.setId(2);
        fileBean.setFiles(bytes);

        userMapper.insertFile(fileBean);
    }

    public byte[] selectFile() {
        FileBean fileBean = userMapper.selectFile();
        byte[] files = fileBean.getFiles();
        return files;
    }

    public ResponseEntity<byte[]> byteToFile(){
        byte[] bytes = readFile();

        System.out.println();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment", "shirs1111111.xlsx");// 文件的属性，也就是文件叫什么吧
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        ResponseEntity responseEntity = new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
        return responseEntity;
    }

    public byte[] readFile() {
        byte[] buffer = null;
        try {
            File file = new File("E:\\agile_boot_file\\2021\\07\\25\\shirs1111111.xlsx");
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;

    }
}
