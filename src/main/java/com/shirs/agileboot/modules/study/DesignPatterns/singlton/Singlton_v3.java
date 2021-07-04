package com.shirs.agileboot.modules.study.DesignPatterns.singlton;


public class Singlton_v3 {

    //构造器私有，无法通过外部创建实例
    private Singlton_v3(){

    }

    private static class Singlton{
        private static Singlton_v3 singlton_v3 = new Singlton_v3();
    }

    private static Singlton_v3 singlton_v2;

    public static Singlton_v3 getInstance(){
        return Singlton.singlton_v3;
    }
}
