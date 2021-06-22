package com.shirs.agileboot.modules.study.DesignPatterns.factory;

public class SmsFactory implements Provider{
    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
