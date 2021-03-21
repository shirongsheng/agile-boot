package com.shirs.agileboot.modules.system.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReplaceString implements Serializable {
    private String origin;
    private String target;
    private String content;
    private String result;
}
