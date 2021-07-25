package com.shirs.agileboot.test;

import org.springframework.boot.CommandLineRunner;

import javax.annotation.PostConstruct;

//@Component
public class Consumer implements CommandLineRunner {


    @PostConstruct
    public void test(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                wait1();
            }
        }).start();
    }

    @Override
    public void run(String... args) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //wait1();
            }
        }).start();
    }

    public void wait1(){
        while (true){
            System.out.println("wait for message11...");
            sleep(1000);
        }
    }

    public void sleep(long mills){
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
