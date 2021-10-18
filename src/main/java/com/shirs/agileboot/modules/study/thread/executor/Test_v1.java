package com.shirs.agileboot.modules.study.thread.executor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test_v1 {

    public static void main(String[] args) throws Exception {

        //countdownlatch
        int num = 0;
        CountDownLatch countDownLatch = new CountDownLatch(10);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable_v1(countDownLatch, num));
        }
        countDownLatch.await();
        System.out.println("num=" + num);
    }
}
