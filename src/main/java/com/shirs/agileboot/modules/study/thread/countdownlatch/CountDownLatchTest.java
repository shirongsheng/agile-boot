package com.shirs.agileboot.modules.study.thread.countdownlatch;

import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {

    static JSONObject details = new JSONObject();

    static JSONObject comments = new JSONObject();

    static JSONObject baseInfo = new JSONObject();

    static CountDownLatch countDownLatch = new CountDownLatch(3);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        System.out.println("线程：" + Thread.currentThread().getName() + "完成CountDownLatch初始化，CountDownLatch=" + countDownLatch);
        executorService.execute(new GetBaseInfoRunnable(countDownLatch, details));
        executorService.execute(new GetCommentRunnable(countDownLatch, details));
        //等待所有线程执行完
        countDownLatch.await();
        Thread.sleep(1000);
        System.out.println("线程：" + Thread.currentThread().getName() + "等待所有线程执行完毕了，CountDownLatch=" + countDownLatch);
        System.out.println("返回详情给前端：details=" + details);
    }
}
