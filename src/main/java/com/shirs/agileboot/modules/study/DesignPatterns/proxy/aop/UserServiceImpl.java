package com.shirs.agileboot.modules.study.DesignPatterns.proxy.aop;

public class UserServiceImpl implements UserService{
    @Override
    public void delete() {
        System.out.println("已删除1条数据");
    }
}
