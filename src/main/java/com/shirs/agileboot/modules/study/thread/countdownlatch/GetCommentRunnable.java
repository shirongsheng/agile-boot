package com.shirs.agileboot.modules.study.thread.countdownlatch;

import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.CountDownLatch;

public class GetCommentRunnable implements Runnable{

    private CountDownLatch countDownLatch;

    private JSONObject details;

    public GetCommentRunnable(CountDownLatch countDownLatch,JSONObject details){
        this.countDownLatch = countDownLatch;
        this.details = details;
    }

    @Override
    public void run() {
        //调用评论模块
        JSONObject comments = new Comment().getComments();
        details.put("comments",comments);
        countDownLatch.countDown();
        System.out.println("线程：" + Thread.currentThread().getName() + "执行完毕，CountDownLatch=" + countDownLatch);
    }
}
