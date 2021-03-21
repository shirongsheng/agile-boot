package com.shirs.agileboot.modules.system.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {

    private String id;

    private Integer age;

    private String name;

    private String password;

    private String sex;
}
