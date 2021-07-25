package com.shirs.agileboot.modules.system.entity;

import com.shirs.agileboot.common.page.PageRequest;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
public class User extends PageRequest {
    private Integer id;

    private String name;

    private Integer age;

    private String sex;

    private String password;
}