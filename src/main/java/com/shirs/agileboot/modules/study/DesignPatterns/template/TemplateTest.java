package com.shirs.agileboot.modules.study.DesignPatterns.template;

public class TemplateTest {
    public static void main(String[] args) {
        EatTemplate hotPot = new HotPot();
        hotPot.process();
        System.out.println("------------");
        EatTemplate barbecue = new Barbecue();
        barbecue.process();
    }
}
