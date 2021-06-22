package com.shirs.agileboot.modules.study.DesignPatterns.builder;

public class User {

    private String name;

    private int age;

    public User(Builder builder){
        this.name = builder.name;
        this.age = builder.age;
    }

    public static class Builder{
        private String name;

        private int age;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder age(int age){
            this.age = age;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}
