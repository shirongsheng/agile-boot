package com.shirs.agileboot.modules.study.DesignPatterns.factory;

public class MailFactory implements Provider{
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
