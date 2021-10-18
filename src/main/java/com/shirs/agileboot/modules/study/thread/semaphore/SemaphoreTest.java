package com.shirs.agileboot.modules.study.thread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) {
        //三个柜台办理业务
        Semaphore semaphore = new Semaphore(3);
        //五个人需要办理业务
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println("线程：" + Thread.currentThread().getName() + "正在办理业务");
                        Thread.sleep(5000); //办理时间 5s
                        semaphore.release();
                        System.out.println("线程：" + Thread.currentThread().getName() + "办理完了");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
