package com.shirs.agileboot.modules.study.DesignPatterns.proxy;

public class Cinema implements Movie{

    private Movie movie;

    public Cinema(Movie movie){
        this.movie = movie;
    }

    @Override
    public void play() {
        movie.play();
        moviesAdvertisement();
    }

    public void moviesAdvertisement(){
        System.out.println("现在是电影广告时间，我们将在7月份上映《美国队长》，敬请期待~");
    }
}
