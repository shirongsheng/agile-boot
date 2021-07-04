package com.shirs.agileboot.modules.study.DesignPatterns.proxy.aop;

import net.sf.cglib.proxy.Enhancer;

public class AopTest {
    public static void main(String[] args) {
        UserServiceImpl user = (UserServiceImpl) Enhancer.create(UserServiceImpl.class,new LogAspect());
        user.delete();
    }
}
