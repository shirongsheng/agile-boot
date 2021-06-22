package com.shirs.agileboot.modules.study.DesignPatterns.factory;

public class MailSender implements Sender {
    @Override
    public void send() {
        System.out.println("mail send...");
    }
}
