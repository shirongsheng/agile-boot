package com.shirs.agileboot.modules.study.DesignPatterns.proxy;

public class IronManMovies implements Movie{
    @Override
    public void play() {
        System.out.println("正在放映《钢铁侠》电影");
    }
}
