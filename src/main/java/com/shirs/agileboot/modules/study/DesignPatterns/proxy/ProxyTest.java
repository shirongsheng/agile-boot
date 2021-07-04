package com.shirs.agileboot.modules.study.DesignPatterns.proxy;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        Movie ironManMovies = new IronManMovies();
        Cinema cinema = new Cinema(ironManMovies);
        cinema.play();

        System.out.println("---------------------");

        ShortVideo starIntroduction = new StarIntroduction();
        DouYin douYin = new DouYin(starIntroduction);
        douYin.play();

        System.out.println("----------JDK动态代理------------");
        Movie movie = (Movie) Proxy.newProxyInstance(ironManMovies.getClass().getClassLoader(),
                ironManMovies.getClass().getInterfaces(),
                new JdkProxy(ironManMovies));

        movie.play();

        ShortVideo shortVideo = (ShortVideo) Proxy.newProxyInstance(starIntroduction.getClass().getClassLoader(),
                starIntroduction.getClass().getInterfaces(),
                new JdkProxy(starIntroduction));

        shortVideo.play();

        System.out.println("----------JDK动态代理------------");

        SpiderManMovies spiderManMovies = (SpiderManMovies) Enhancer.create(SpiderManMovies.class,
                new CglibProxy());
        spiderManMovies.play();
    }
}
