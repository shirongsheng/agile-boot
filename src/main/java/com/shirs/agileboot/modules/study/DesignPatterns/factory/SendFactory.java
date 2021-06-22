package com.shirs.agileboot.modules.study.DesignPatterns.factory;

public class SendFactory {

    public Sender produce(String type){
        if ("sms".equals(type)){
            return new SmsSender();
        }else if ("mail".equals(type)){
            return new MailSender();
        }else {
            System.out.println("no such type");
            return null;
        }
    }
}
