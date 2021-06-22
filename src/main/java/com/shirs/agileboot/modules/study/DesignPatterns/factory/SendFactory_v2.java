package com.shirs.agileboot.modules.study.DesignPatterns.factory;

public class SendFactory_v2 {

    public Sender produceMail(){
        return  new MailSender();
    }

    public Sender produceSms(){
        return  new SmsSender();
    }
}
