package com.shirs.agileboot.modules.study.thread.countdownlatch;

import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.CountDownLatch;

public class GetBaseInfoRunnable implements Runnable{

    private CountDownLatch countDownLatch;

    private JSONObject details;

    public GetBaseInfoRunnable(CountDownLatch countDownLatch, JSONObject details){
        this.countDownLatch = countDownLatch;
        this.details = details;
    }

    @Override
    public void run() {
        //调用基础信息模块
        JSONObject baseInfo = new BaseInfo().getBaseInfo();
        details.put("baseInfo",baseInfo);
        countDownLatch.countDown();
        System.out.println("线程：" + Thread.currentThread().getName() + "执行完毕，CountDownLatch=" + countDownLatch);
    }
}
