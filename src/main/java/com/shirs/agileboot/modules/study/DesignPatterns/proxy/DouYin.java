package com.shirs.agileboot.modules.study.DesignPatterns.proxy;

public class DouYin implements ShortVideo{

    private ShortVideo shortVideo;

    public DouYin(ShortVideo shortVideo){
        this.shortVideo = shortVideo;
    }

    @Override
    public void play() {
        douyinAdvertisement();
        shortVideo.play();
    }

    public void douyinAdvertisement(){
        System.out.println("欢迎来抖音观看短视频，看视频赢大礼！");
    }
}
