package com.shirs.agileboot.modules.study.DesignPatterns.factory;

public class SendFactory_v3 {

    public static Sender produceMail(){
        return  new MailSender();
    }

    public static Sender produceSms(){
        return  new SmsSender();
    }
}
