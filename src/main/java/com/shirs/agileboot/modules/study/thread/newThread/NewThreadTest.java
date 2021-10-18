package com.shirs.agileboot.modules.study.thread.newThread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class NewThreadTest {
    public static void main(String[] args) {

        //继承Thread类
        /*NewThread_v1 newThread_v1 = new NewThread_v1();
        NewThread_v1 newThread_v2 = new NewThread_v1();
        newThread_v1.start();
        newThread_v2.start();*/

        //实现runable接口
        /*NewThread_v2 newThread_v2 = new NewThread_v2();
        new Thread(newThread_v2).start();
        new Thread(newThread_v2).start();*/

        //实现Callable接口
        NewThread_v3 newThread_v3 = new NewThread_v3();
        FutureTask futureTask = new FutureTask<>(newThread_v3);
        FutureTask futureTask2 = new FutureTask<>(newThread_v3);
        new Thread(futureTask).start();
        new Thread(futureTask2).start();
        try {
            System.out.println("获取返回值：" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
