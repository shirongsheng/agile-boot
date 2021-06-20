package com.shirs.agileboot.modules.study.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PostConstructTest {

    @Autowired
    private InitClass initClass;

    private static String name = "";

    @PostConstruct
    public void test(){
        name = initClass.getName();
        System.out.println("name----"+name);
    }
}