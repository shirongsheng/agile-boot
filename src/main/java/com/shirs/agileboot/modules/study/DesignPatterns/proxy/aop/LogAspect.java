package com.shirs.agileboot.modules.study.DesignPatterns.proxy.aop;

import java.lang.reflect.Method;

public class LogAspect extends BaseAspect{
    @Override
    public boolean isInterceptor(Method method) {
        return method.getName().equals("delete");
    }

    public void before(){
        System.out.println("删除方法开始...");
    }

    public void after(){
        System.out.println("删除方法结束...");
    }
}
