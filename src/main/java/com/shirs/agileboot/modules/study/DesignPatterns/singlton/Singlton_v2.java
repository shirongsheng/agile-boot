package com.shirs.agileboot.modules.study.DesignPatterns.singlton;

public class Singlton_v2 {

    //构造器私有，无法通过外部创建实例
    private Singlton_v2(){

    }

    private static Singlton_v2 singlton_v2;

    public static synchronized Singlton_v2 getInstance(){
        if (singlton_v2 == null){
            synchronized (singlton_v2){
                if (singlton_v2 ==null){
                    singlton_v2 = new Singlton_v2();
                }
            }

        }
        return singlton_v2;
    }
}
