package com.shirs.agileboot.modules.study.DesignPatterns.factory;

public class FactoryTest {
    public static void main(String[] args) {
        //普通工厂模式
        /*new SendFactory().produce("sms").send();
        new SendFactory().produce("mail").send();*/

        //多个方法工厂模式
        //new SendFactory_v2().produceMail().send();
        //new SendFactory_v2().produceSms().send();

        //静态工厂方法模式
        //SendFactory_v3.produceMail().send();
        //SendFactory_v3.produceSms().send();

        //抽象工厂方法模式
        new SmsFactory().produce().send();
        new MailFactory().produce().send();
    }
}
