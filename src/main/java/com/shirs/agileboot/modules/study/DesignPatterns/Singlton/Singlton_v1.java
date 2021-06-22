package com.shirs.agileboot.modules.study.DesignPatterns.Singlton;

public class Singlton_v1 {

    //构造器私有，无法通过外部创建实例
    private Singlton_v1(){

    }

    private static Singlton_v1 singlton_v1 = new Singlton_v1();

    public static Singlton_v1 getInstance(){
        return singlton_v1;
    }
}
