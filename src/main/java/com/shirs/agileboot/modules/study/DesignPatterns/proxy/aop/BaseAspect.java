package com.shirs.agileboot.modules.study.DesignPatterns.proxy.aop;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public abstract class BaseAspect implements MethodInterceptor {

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        if (isInterceptor(method)){
            before();
            methodProxy.invokeSuper(object,args);
            after();
        }else
            methodProxy.invokeSuper(object,args);
        return object;
    }

    public void before(){

    }

    public void after(){

    }

    public abstract boolean isInterceptor(Method method);
}
