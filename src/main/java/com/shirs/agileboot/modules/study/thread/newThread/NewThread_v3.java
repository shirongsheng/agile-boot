package com.shirs.agileboot.modules.study.thread.newThread;

import java.util.concurrent.Callable;

public class NewThread_v3 implements Callable {
    @Override
    public Object call() throws Exception {
        String result = Thread.currentThread().getName() + "启动了";
        System.out.println(result);
        return result;
    }
}
