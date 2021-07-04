package com.shirs.agileboot.modules.study.DesignPatterns.proxy;

public class StarIntroduction implements ShortVideo{
    @Override
    public void play() {
        System.out.println("正在播放吴思聪的复出介绍~");
    }
}
