package com.shirs.agileboot.modules.study.transactional;

import com.shirs.agileboot.modules.system.mapper.FileMapper;
import com.shirs.agileboot.modules.system.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Transactional_v1 {

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private UserMapper userMapper;

    public static void main(String[] args) {

    }

    private static void executeA(){

    }
}
