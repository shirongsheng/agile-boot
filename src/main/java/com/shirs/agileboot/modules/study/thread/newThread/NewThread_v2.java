package com.shirs.agileboot.modules.study.thread.newThread;

public class NewThread_v2 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "启动了");
    }
}
