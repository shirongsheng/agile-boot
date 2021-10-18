package com.shirs.agileboot.modules.study.thread.newThread;

public class NewThread_v1 extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "启动了");
    }
}
