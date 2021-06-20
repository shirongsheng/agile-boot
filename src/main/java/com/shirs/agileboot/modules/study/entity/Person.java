package com.shirs.agileboot.modules.study.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Person {

    public Person(String name,Integer age){
        this.name = name;
        this.age = age;
    }

    private String name;

    private Integer age;

    public static List<Person> getPersonList(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("tom",24));
        personList.add(new Person("bob",24));
        personList.add(new Person("july",24));
        return personList;
    }
}
