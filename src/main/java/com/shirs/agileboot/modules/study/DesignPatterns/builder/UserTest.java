package com.shirs.agileboot.modules.study.DesignPatterns.builder;

public class UserTest {

    public static void main(String[] args) {
        User shirs = new User.Builder().name("shirs").age(24).build();
        System.out.println(shirs);
    }
}
