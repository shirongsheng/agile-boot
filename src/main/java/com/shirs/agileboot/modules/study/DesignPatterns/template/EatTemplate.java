package com.shirs.agileboot.modules.study.DesignPatterns.template;

/**
 * 我们下馆子吃饭，通常有三个步骤
 * 1、下单
 * 2、吃饭
 * 3、结账
 * 我们可以将下单和结账作为不变的部分，而吃的东西会因为不同的馆子不一样
 *
 * 我们在这个吃饭的模板里定义了我们吃饭的步骤，这个算法的框架时不变的；
 * 但是我们去不同的馆子会吃到不同的东西，不同的子类就有不同的实现。
 *
 * 模板模式其实就是封装不变的部分，扩展可变的部分的一个很好的体现。
 */
public abstract class EatTemplate {

    public void order(){
        System.out.println("下单完成...");
    }

    public abstract void eat();

    public void pay(){
        System.out.println("吃完付账...");
    }

    public void process(){
        this.order();
        this.eat();
        this.pay();
    }
}
