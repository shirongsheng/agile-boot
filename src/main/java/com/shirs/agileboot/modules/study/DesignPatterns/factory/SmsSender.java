package com.shirs.agileboot.modules.study.DesignPatterns.factory;

public class SmsSender implements Sender {
    @Override
    public void send() {
        System.out.println("sms send...");
    }
}
