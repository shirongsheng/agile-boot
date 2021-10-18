package com.shirs.agileboot.modules.study.thread.newThread;

public class RunStartTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("新建线程:" + Thread.currentThread().getName() + "，启动了");
            }
        });
        thread.start();
        System.out.println("主线程:" + Thread.currentThread().getName() + "，启动了");
    }
}
