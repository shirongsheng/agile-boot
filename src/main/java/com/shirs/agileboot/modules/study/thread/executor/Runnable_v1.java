package com.shirs.agileboot.modules.study.thread.executor;

import java.util.concurrent.CountDownLatch;

public class Runnable_v1 implements Runnable {

    private CountDownLatch countDownLatch;

    private int num;

    public Runnable_v1(CountDownLatch countDownLatch, int num) {
        this.countDownLatch = countDownLatch;
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            num++;
        }
        countDownLatch.countDown();
        System.out.println(Thread.currentThread().getName() + "---num=" + num);
    }
}
